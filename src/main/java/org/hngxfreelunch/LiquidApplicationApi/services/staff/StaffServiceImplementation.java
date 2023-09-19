package org.hngxfreelunch.LiquidApplicationApi.services.staff;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.StaffRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StaffServiceImplementation implements StaffService {

    private StaffRepository staffRepository;

}
