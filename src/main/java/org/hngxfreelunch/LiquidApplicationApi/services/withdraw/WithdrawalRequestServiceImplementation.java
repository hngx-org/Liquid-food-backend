package org.hngxfreelunch.LiquidApplicationApi.services.withdraw;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.WithdrawalResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Staff;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.WithdrawalRequest;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.StaffRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.WithdrawalRequestRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WithdrawalRequestServiceImplementation implements WithdrawalRequestService {

    private final WithdrawalRequestRepository withdrawalRequestRepository;

    private final StaffRepository staffRepository;

    @Override
    public WithdrawalResponse addWithdrawalRequest(Long staffId, Integer creditsToWithdraw) {
        // Find the staff member by ID
        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new EntityNotFoundException("Staff member not found"));

        // Create a new WithdrawalRequest
        WithdrawalRequest withdrawalRequest = new WithdrawalRequest();
        withdrawalRequest.setStaff(staff);
        withdrawalRequest.setLunchCredits(creditsToWithdraw);

        // Save the withdrawal request & return a response
        withdrawalRequestRepository.save(withdrawalRequest);
        return WithdrawalResponse
                .builder()
                .message("Withdrawal request created successfully with ID:" + withdrawalRequest.getId())
                .build();
    }
}
