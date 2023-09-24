package org.hngxfreelunch.LiquidApplicationApi.services.lunch;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LunchRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LunchResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Lunches;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.LunchRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.FreeLunchException;
import org.hngxfreelunch.LiquidApplicationApi.utils.DateUtils;
import org.hngxfreelunch.LiquidApplicationApi.utils.LunchUtils;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LunchServiceImplementation implements LunchService {

    private final LunchRepository lunchRepository;
    private final UserRepository userRepository;
    private final UserUtils userUtils;
    private final LunchUtils lunchUtils;


    @Override
    public ApiResponseDto<LunchResponse> sendLunch(Long receiverId, LunchRequestDto lunchRequestDto){
        User sender = userUtils.getLoggedInUser();
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(()-> new FreeLunchException("User does not exist"));
        if(!sender.getOrganizations().equals(receiver.getOrganizations())){
            throw new FreeLunchException("Receiver must be in your organization");
        }
        if(sender.equals(receiver)){
            throw new FreeLunchException("You cannot send lunch to yourself");
        }
        User theSender;
        if(sender.getLunchCreditBalance().compareTo(BigInteger.valueOf(lunchRequestDto.getQuantity()))>=0){
            BigInteger balanceSender = sender.getLunchCreditBalance().subtract(BigInteger.valueOf(lunchRequestDto.getQuantity()));
            sender.setLunchCreditBalance(balanceSender);
            theSender = userRepository.save(sender);
            Lunches lunches = lunchRepository.save(Lunches.builder()
                    .sender(theSender)
                    .receiver(receiver)
                    .quantity(lunchRequestDto.getQuantity())
                    .note(lunchRequestDto.getNote())
                    .organizations(theSender.getOrganizations())
                    .redeemed(false)
                    .createdAt(DateUtils.saveDate(LocalDateTime.now()))
                    .updatedAt(DateUtils.saveDate(LocalDateTime.now()))
                    .build());
            return new ApiResponseDto<>("Lunch sent successfully", 200,lunchUtils.mapLunchesToDto(lunches));
        }else{
            return new ApiResponseDto<>("Insufficient Lunch Credits", 400, null);
        }
    }

    @Override
    public ApiResponseDto<LunchResponse> getLunch(Long lunchId){
        User user = userUtils.getLoggedInUser();
        Lunches lunches = lunchRepository.findById(lunchId)
                .orElseThrow(()->new FreeLunchException("Lunch not found"));
        if(!lunches.getSender().getEmail().equals(user.getEmail()) && !lunches.getReceiver().getEmail().equals(user.getEmail())){
            throw new FreeLunchException("Unauthorized to view this lunch");
        }

        return new ApiResponseDto<>("Lunch fetched Successfully", 200, lunchUtils.mapLunchesToDto(lunches));
    }

    @Override
    public ApiResponseDto<List<LunchResponse>> getAllUserLunches(){
        User user = userUtils.getLoggedInUser();
        List<LunchResponse> lunches = lunchRepository.findAllBySenderOrReceiver(user, user)
                .stream().map(lunchUtils::mapLunchesToDto).toList();
        return new ApiResponseDto<>("Lunches fetched Successfully", 200, lunches);
    }

    @Override
    public ApiResponseDto<List<LunchResponse>> getAllPendingLunches(){
        List<LunchResponse> lunches = getAllUserLunches().getData()
                .stream().filter(lunch -> !lunch.getRedeemed()).toList();
        return new ApiResponseDto<>("Lunches fetched Successfully", 200, lunches);
    }

    @Override
    public ApiResponseDto<List<LunchResponse>> getAllRedeemedLunches(){
        List<LunchResponse> lunches = getAllUserLunches().getData()
                .stream().filter(LunchResponse::getRedeemed).toList();
        return new ApiResponseDto<>("Lunches fetched Successfully", 200, lunches);
    }

    @Override
    public  ApiResponseDto<List<LunchResponse>> getAllSentLunchesByUser(){
        User user = userUtils.getLoggedInUser();
        List<LunchResponse> lunches = getAllUserLunches().getData()
                .stream().filter(lunch -> Objects.equals(lunch.getSender().getId(), user.getId())).toList();
        return new ApiResponseDto<>("Lunches fetched Successfully", 200, lunches);
    }

    @Override
    public  ApiResponseDto<List<LunchResponse>> getAllReceivedLunchesByUser(){
        User user = userUtils.getLoggedInUser();
        List<LunchResponse> lunches = getAllUserLunches().getData()
                .stream().filter(lunch -> Objects.equals(lunch.getReceiver().getId(), user.getId())).toList();
        return new ApiResponseDto<>("Lunches fetched Successfully", 200, lunches);
    }

    @Override
    public ApiResponseDto<List<LunchResponse>> getAllOrganizationLunches(){
        User user = userUtils.getLoggedInUser();
        if(!user.getIsAdmin()){
            throw new FreeLunchException("User not authorized to view this");
        }
        List<LunchResponse> lunches = lunchRepository.findAllByOrganizations(user.getOrganizations())
                .stream().map(lunchUtils::mapLunchesToDto).toList();
        return new ApiResponseDto<>("Lunches fetched Successfully", 200, lunches);
    }

    @Override
    public ApiResponseDto<LunchResponse> redeemLunch(Long lunchId){
        User user = userUtils.getLoggedInUser();
        Lunches lunches = lunchRepository.findById(lunchId)
                .orElseThrow(()-> new FreeLunchException("Lunch not found"));
        if(!user.equals(lunches.getReceiver())){
            throw new FreeLunchException("User cannot redeem this lunch");
        }
        BigInteger balanceReceiver = user.getLunchCreditBalance().add(BigInteger.valueOf(lunches.getQuantity()));
        user.setLunchCreditBalance(balanceReceiver);
        userRepository.save(user);
        lunches.setRedeemed(true);
        lunches.setUpdatedAt(DateUtils.saveDate(LocalDateTime.now()));
        Lunches theLunch = lunchRepository.save(lunches);
        return new ApiResponseDto<>("Lunch Redeemed Successfully", 200, lunchUtils.mapLunchesToDto(theLunch));
    }
    @Override
    public ApiResponseDto<LunchResponse> cancelLunch(Long lunchId){
        User user = userUtils.getLoggedInUser();
        Lunches lunches = lunchRepository.findById(lunchId)
                .orElseThrow(()-> new FreeLunchException("Lunch not found"));
        if(!Objects.equals(lunches.getReceiver().getId(), user.getId()) && !Objects.equals(lunches.getSender().getId(), user.getId())){
            throw new FreeLunchException("User cannot redeem this lunch");
        }
        if(lunches.getRedeemed()){
            throw new FreeLunchException("Lunch has already been redeemed");
        }
        User sender = lunches.getSender();
        BigInteger balanceSender = sender.getLunchCreditBalance().add(BigInteger.valueOf(lunches.getQuantity()));
        sender.setLunchCreditBalance(balanceSender);
        userRepository.save(sender);
        lunches.setUpdatedAt(DateUtils.saveDate(LocalDateTime.now()));
        Lunches theLunch = lunchRepository.save(lunches);
        return new ApiResponseDto<>("Lunch Redeemed Successfully", 200, lunchUtils.mapLunchesToDto(theLunch));
    }
}
