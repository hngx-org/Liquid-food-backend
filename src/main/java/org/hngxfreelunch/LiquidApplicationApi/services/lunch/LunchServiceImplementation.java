package org.hngxfreelunch.LiquidApplicationApi.services.lunch;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LunchRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LunchResponseDto;
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
    public List<LunchResponseDto> sendLunch(LunchRequestDto lunchRequestDto) {
        User sender = userUtils.getLoggedInUser();
        List<User> user= staffRepository.findAllById(lunchRequestDto.getReceiverId());
        List<Lunches> lunchesList=user.stream()
                .map(eachStaff->sendLunchToEachStaff(eachStaff, sender,lunchRequestDto))
                .toList();
        return lunchesList.stream()
                .map(this::mapLunchToResponseDto)
                .toList();
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

    private LunchResponseDto mapLunchToResponseDto(Lunches eachLunch) {
        return LunchResponseDto.builder()
                .sender(eachLunch.getSender())
                .receiver(eachLunch.getReceiver())
                .quantity(eachLunch.getQuantity())
                .redeemed(eachLunch.getRedeemed())
                .createdAt(eachLunch.getCreatedAt())
                .build();
    }

    private Lunches sendLunchToEachStaff(User eachStaff, User sender,LunchRequestDto lunchRequestDto) {
        User receiver = staffRepository.findById(eachStaff.getId()).get();
        Lunches newLunch= Lunches.builder()
                .sender(sender)
                .receiver(receiver)
                .redeemed(false)
                .note(lunchRequestDto.getNote())
                .createdAt(LocalDateTime.now())
                .quantity(lunchRequestDto.getQuantity())
                .build();
        receiver.setLunchCreditBalance(receiver.getLunchCreditBalance().add(BigInteger.valueOf(lunchRequestDto.getQuantity())));
        staffRepository.save(receiver);
        return lunchRepository.save(newLunch);
    }

    private Lunches sendLunchToEachStaff(User eachStaff, User sender,String note, Integer quantity) {
        Lunches newLunch= Lunches.builder()
                .sender(sender)
                .receiver(staffRepository.findById(eachStaff.getId()).get())
                .redeemed(false)
                .note(note)
                .createdAt(LocalDateTime.now())
                .quantity(quantity)
                .build();
        return lunchRepository.save(newLunch);
    }

    @Override
    public List<LunchResponseDto> getAllLunch() {
        List<Lunches> lunchesList=lunchRepository.findAll();
        return lunchesList.stream()
                .map(this::mapLunchToResponseDto)
                .toList();
    }

    @Override
    public LunchResponseDto getLunch(Long lunch_id) {
        Lunches lunches= lunchRepository.findById(lunch_id).get();
        return mapLunchToResponseDto(lunches);
    }

}
