package org.hngxfreelunch.LiquidApplicationApi.services.lunch;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LunchRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LunchResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Lunches;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.LunchRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LunchServiceImplementation implements LunchService {

    private final LunchRepository lunchRepository;

    private final UserRepository staffRepository;


    @Override
    public List<LunchResponseDto> sendLunch(LunchRequestDto lunchRequestDto, HttpServletRequest request) {
        User sender= getSender(request);
        List<User> user= staffRepository.findAllById(lunchRequestDto.getReceiverId());
        List<Lunches> lunchesList=user.stream()
                .map(eachStaff->sendLunchToEachStaff(eachStaff,sender,lunchRequestDto))
                .toList();
        return lunchesList.stream()
                .map(eachLunch->mapLunchToResponseDto(eachLunch))
                .toList();
    }

    private User getSender(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");
        return user;
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
        Lunches newLunch= Lunches.builder()
                .sender(sender)
                .receiver(staffRepository.findById(eachStaff.getId()).get())
                .redeemed(false)
                .note(lunchRequestDto.getNote())
                .createdAt(LocalDateTime.now())
                .quantity(lunchRequestDto.getQuantity())
                .build();
        return lunchRepository.save(newLunch);
    }

    @Override
    public List<LunchResponseDto> getAllLunch() {
        List<Lunches> lunchesList=lunchRepository.findAll();
        List<LunchResponseDto> staffLunch=lunchesList.stream()
                .map(eachLunch->mapLunchToResponseDto(eachLunch))
                .toList();
        return staffLunch;
    }

    @Override
    public LunchResponseDto getLunch(Long lunch_id) {
        Lunches lunches= lunchRepository.findById(lunch_id).get();
        return mapLunchToResponseDto(lunches);
    }

}
