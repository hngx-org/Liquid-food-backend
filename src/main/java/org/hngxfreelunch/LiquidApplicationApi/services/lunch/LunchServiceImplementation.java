<<<<<<< HEAD
package org.hngxfreelunch.LiquidApplicationApi.services.lunch;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.LunchRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LunchServiceImplementation implements LunchService {

    private LunchRepository lunchRepository;

}
=======
package org.hngxfreelunch.LiquidApplicationApi.services.lunch;

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
    public List<LunchResponseDto> sendLunch(LunchRequestDto lunchRequestDto, User sender) {
        List<User> user= staffRepository.findAllById(lunchRequestDto.getReceiverId());
        List<Lunches> lunchesList=user.stream()
                .map(eachStaff->sendLunchToEachStaff(eachStaff,sender,lunchRequestDto))
                .toList();
        return lunchesList.stream()
                .map(eachLunch->mapLunchToResponseDto(eachLunch))
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
    public List<LunchResponseDto> getAllLunch(Long staff_id) {
        User user= staffRepository.findById(staff_id).get();
        List<Lunches> lunchesList=lunchRepository.findAll();
        List<LunchResponseDto> staffLunch=lunchesList.stream()
                .filter(p->p.getId().equals(user.getId()))
                .map(o->mapLunchToResponseDto(o))
                .toList();
        return staffLunch;
    }

    @Override
    public LunchResponseDto getLunch(Long lunch_id) {
        Lunches lunches= lunchRepository.findById(lunch_id).get();
        return mapLunchToResponseDto(lunches);
    }

}
>>>>>>> 37617686d864be3d5717d47a2fb029fe19dd3361
