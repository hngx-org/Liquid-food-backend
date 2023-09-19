package org.hngxfreelunch.LiquidApplicationApi.services.staff;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Staff;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StaffServiceImplementation implements StaffService {

    private StaffRepository staffRepository;


    @Override
    public void addStaff(Staff staff) {
        staffRepository.save(staff);
    }

    @Override
    public Staff getStaff(Long id) {
        Optional<Staff> staff = staffRepository.findById(id);
        if (staff.isEmpty()) {
            return null;
        }
        return staff.get();
    }

    @Override
    public void updateStaff(Long id, Staff updatedStaff) {
        Staff staff = getStaff(id);
        if (staffRepository.findById(id).isEmpty()) {
            return;
        }
        updatedStaff.setId(id);
        staffRepository.save(updatedStaff);
    }
}
