package org.hngxfreelunch.LiquidApplicationApi.services.withdraw;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.WithdrawalRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.WithdrawalResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Status;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Withdrawals;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.WithdrawalRepository;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WithdrawalRequestServiceImplementation implements WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;

    private final UserRepository userRepository;
    private final UserUtils userUtils;

    @Transactional
    public ApiResponseDto<?> processWithdrawalRequest(WithdrawalRequestDto withdrawalRequestDto) {

        // Step 1: Get the currently logged-in user
        User loggedInUser = userUtils.getLoggedInUser();

        // Step 2: Get the user's lunch credit balance
        BigInteger lunchCreditBalance = loggedInUser.getLunchCreditBalance();

        // Step 3: Validate that the user has sufficient lunch credit balance
        if (lunchCreditBalance.compareTo(BigInteger.ZERO) <= 0) {
            throw new IllegalArgumentException("No lunch credits available for withdrawal");
        }

        // Step 4: Get the bank details from the withdrawalRequestDto. But it won't be used
        String bankNumber = withdrawalRequestDto.getBankNumber();
        String bankCode = withdrawalRequestDto.getBankCode();
        String bankName = withdrawalRequestDto.getBankName();

        // Step 5: Create a withdrawal entity
        Withdrawals withdrawal = new Withdrawals();
        withdrawal.setUser(loggedInUser);
        withdrawal.setStatus(Status.PENDING);
        withdrawal.setAmount(lunchCreditBalance);
        withdrawal.setCreatedAt(LocalDateTime.now());

        // Step 6: Update the user's lunch credit balance & save it
        loggedInUser.setLunchCreditBalance(BigInteger.ZERO);
        userRepository.save(loggedInUser);

        withdrawal.setStatus(Status.SUCCESS);
        Withdrawals savedWithdrawal = withdrawalRepository.save(withdrawal);

        // Step 7: Create a response DTO
        WithdrawalResponseDto withdrawalResponseDto = WithdrawalResponseDto.builder()
                .id(savedWithdrawal.getId())
                .userId(loggedInUser.getId())
                .status(savedWithdrawal.getStatus())
                .amount(savedWithdrawal.getAmount())
                .createdAt(savedWithdrawal.getCreatedAt())
                .build();

        // Step 8: Return the response with success status
        return new ApiResponseDto<>("Withdrawal request created successfully", HttpStatus.OK.value(),withdrawalResponseDto);
    }
}
