package org.hngxfreelunch.LiquidApplicationApi.services.lunch;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LunchRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LunchResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.UsersResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Lunches;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.LunchRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.LunchNotFoundException;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LunchServiceImplementation implements LunchService {

    private final LunchRepository lunchRepository;

    private final UserRepository staffRepository;


    @Override
    public List<LunchResponseDto> sendLunch(LunchRequestDto lunchRequestDto, User sender) {
        List<User> user= staffRepository.findAllById(lunchRequestDto.getReceiverId());
        List<Lunches> lunchesList=user.stream()
                .map(eachStaff->sendLunchToEachStaff(eachStaff,sender,lunchRequestDto))
                .toList();
        return lunchesList.stream()
                .map(this::mapLunchToResponseDto)
                .toList();
    }

    private LunchResponseDto mapLunchToResponseDto(Lunches eachLunch) {
        return LunchResponseDto.builder()
                .sender(UsersResponseDto.builder()
                        .id(eachLunch.getSender().getId())
                        .email(eachLunch.getSender().getEmail())
                        .organizationName(eachLunch.getSender().getOrganizations().getName())
                        .fullName(eachLunch.getSender().getFirstName() + " " + eachLunch.getSender().getLastName())
                        .build())
                .receiver(UsersResponseDto.builder()
                        .id(eachLunch.getReceiver().getId())
                        .email(eachLunch.getReceiver().getEmail())
                        .organizationName(eachLunch.getReceiver().getOrganizations().getName())
                        .fullName(eachLunch.getReceiver().getFirstName() + " " + eachLunch.getReceiver().getLastName())
                        .build())
                .quantity(eachLunch.getQuantity())
                .redeemed(eachLunch.getRedeemed())
                .createdAt(eachLunch.getCreatedAt())
                .build();
    }

    private Lunches sendLunchToEachStaff(User eachStaff, User sender,LunchRequestDto lunchRequestDto) {
        Lunches newLunch= Lunches.builder()
                .sender(sender)
                .receiver(staffRepository.findById(eachStaff.getId()).orElseThrow(() -> new UserNotFoundException("User with id " + eachStaff.getId() + " not found")))
                .redeemed(false)
                .note(lunchRequestDto.getNote())
                .quantity(lunchRequestDto.getQuantity())
                .build();
        return lunchRepository.save(newLunch);
    }

    @Override
    public List<LunchResponseDto> getAllLunch(Long staffId) {
        User user= staffRepository.findById(staffId).orElseThrow(() -> new UserNotFoundException("User with id " + staffId + " not found"));
        List<Lunches> lunchesList=lunchRepository.findAll();
        return lunchesList.stream()
                .filter(p->p.getId().equals(user.getId()))
                .map(this::mapLunchToResponseDto)
                .toList();
    }

    @Override
    public LunchResponseDto getLunch(Long lunchId) {
        Lunches lunches= lunchRepository.findById(lunchId).orElseThrow(LunchNotFoundException::new);
        return mapLunchToResponseDto(lunches);
    }

}
