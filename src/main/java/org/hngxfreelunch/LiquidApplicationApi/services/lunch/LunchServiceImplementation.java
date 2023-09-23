package org.hngxfreelunch.LiquidApplicationApi.services.lunch;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LunchRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LunchResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.UsersResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Lunches;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.LunchRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LunchServiceImplementation implements LunchService {

    private final LunchRepository lunchRepository;
    private final UserRepository staffRepository;
    private final UserUtils userUtils;


    @Override
    public LunchResponseDto sendLunch(LunchRequestDto lunchRequestDto) {
        User sender = userUtils.getLoggedInUser();
        User receiver= staffRepository.findById(lunchRequestDto.getReceiverId()).get();
        Lunches sentLunch= sendingLunch(sender,receiver,lunchRequestDto);
        LunchResponseDto sentLunchResponse= mapLunchToResponseDto(sentLunch);
        return sentLunchResponse;
    }

    @Override
    public List<LunchResponseDto> sendLunch(String note, Integer quantity, User sender) {
        List<User> user= staffRepository.findAllByOrganizations_Id(sender.getOrganizations().getId());
        List<Lunches> lunchesList=user.stream()
                .map(eachStaff->sendLunchToEachStaff(eachStaff, sender,note,quantity))
                .toList();
        return lunchesList.stream()
                .map(this::mapLunchToResponseDto)
                .toList();

    }

    private Lunches sendingLunch(User sender, User receiver, LunchRequestDto lunchRequestDto) {
        Integer receiverLunchBalance= receiver.getLunchCreditBalance().intValue();
       Lunches sentLunch= Lunches.builder()
                .sender(sender)
                .receiver(receiver)
                .createdAt(LocalDateTime.now())
                .note(lunchRequestDto.getNote())
                .quantity(receiverLunchBalance+lunchRequestDto.getQuantity())
                .build();
        return lunchRepository.save(sentLunch);
    }


    private LunchResponseDto mapLunchToResponseDto(Lunches eachLunch) {
        return LunchResponseDto.builder()
                .sender(mapUserToDTO(eachLunch.getSender()))
                .receiver(mapUserToDTO(eachLunch.getReceiver()))
                .quantity(eachLunch.getQuantity())
                .createdAt(eachLunch.getCreatedAt())
                .build();

    }
    private UsersResponseDto mapUserToDTO(User user){
        return UsersResponseDto.builder().build();
    }

    @Override
    public List<LunchResponseDto> getAllLunch() {
        User user = userUtils.getLoggedInUser();
        List<Lunches> lunchesList=lunchRepository.findAll();
        List<Lunches> userLunch=lunchesList.stream()
                .filter(eachLunch->eachLunch.getSender().equals(user)
                        && eachLunch.getReceiver().equals(user))
                .toList();
        return userLunch.stream()
                .map(lunch->mapLunchToResponseDto(lunch))
                .toList();
    }

    @Override
    public LunchResponseDto getLunch(Long lunch_id) {
        Lunches lunches= lunchRepository.findById(lunch_id).get();
        return mapLunchToResponseDto(lunches);
    }

}
