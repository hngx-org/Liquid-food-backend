package org.hngxfreelunch.LiquidApplicationApi.services.staff;

import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.StaffDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Staff;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.StaffRepository;
import org.hngxfreelunch.LiquidApplicationApi.exceptions.StaffNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffServiceImplementation implements StaffService {

    @Autowired
    private StaffRepository staffRepository;


    @Override
    public ApiResponseDto addStaff(StaffDto staffRequest) {
//        staffRepository.exists();
        Staff newStaff = Staff.builder()
                .firstname(staffRequest.getFirstname())
                .lastname(staffRequest.getLastname())
                .organization(staffRequest.getOrganization())
                .accountDetails(staffRequest.getAccountDetails())
                .lunchCredits(staffRequest.getLunchCredits())
                .lunches_sent(staffRequest.getLunches_sent())
                .stack(staffRequest.getStack())
                .lunches_received(staffRequest.getLunches_received())
                .build();
        staffRepository.save(newStaff);
        return new ApiResponseDto(newStaff,"Staff Created successfully", HttpStatus.SC_CREATED);
    }

    @Override
    public Staff getStaff(Long id) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isEmpty()) {
            throw new StaffNotFoundException("Staff not found");
        }
        return staff.get();
    }

    @Override
    public ApiResponseDto updateStaff(Long id, StaffDto updatedStaff) {
        Staff staff = getStaff(id);

        if (updatedStaff.getFirstname() != null) {
            staff.setFirstname(updatedStaff.getFirstname());
        }
        if (updatedStaff.getLastname() != null) {
            staff.setLastname(updatedStaff.getLastname());
        }
        if (updatedStaff.getLunchCredits() != null) {
            staff.setLunchCredits(updatedStaff.getLunchCredits());
        }
        if (updatedStaff.getOrganization() != null) {
            staff.setOrganization(updatedStaff.getOrganization());
        }
        if (updatedStaff.getStack() != null) {
            staff.setStack(updatedStaff.getStack());
        }
        if (updatedStaff.getAccountDetails() != null) {
            staff.setAccountDetails(updatedStaff.getAccountDetails());
        }
        if (updatedStaff.getLunches_received() != null) {
            staff.setLunches_received(updatedStaff.getLunches_received());
        }
        if (updatedStaff.getLunches_sent() != null) {
            staff.setLunches_sent(updatedStaff.getLunches_sent());
        }
        staffRepository.save(staff);
        return new ApiResponseDto(staff,"Staff Updated successfully", HttpStatus.SC_CREATED);

    }
}
