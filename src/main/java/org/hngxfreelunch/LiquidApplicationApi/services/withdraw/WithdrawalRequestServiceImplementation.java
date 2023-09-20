package org.hngxfreelunch.LiquidApplicationApi.services.withdraw;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.WithdrawalRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.WithdrawalResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Withdrawals;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.WithdrawalRepository;
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

    @Transactional
    public ApiResponseDto<?> processWithdrawalRequest(WithdrawalRequestDto withdrawalRequestDto, Long userId) {

        // Step 1: Find the user by userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userId));

        // Step 2: Get the user's lunch credit balance
        BigInteger lunchCreditBalance = user.getLunch_credit_balance();

        // Step 3: Validate that the user has sufficient lunch credits balance
        if (lunchCreditBalance.compareTo(BigInteger.ZERO) <= 0) {
            throw new IllegalArgumentException("No lunch credits available for withdrawal for user ID: " + user.getId());
        }

        // Step 4: Get the bank account details from withdrawalRequestDto
        String bankNumber = withdrawalRequestDto.getBankNumber();
        String bankCode = withdrawalRequestDto.getBankCode();
        String bankName = withdrawalRequestDto.getBankName();
        BigInteger amount = withdrawalRequestDto.getAmount();

        // Step 5: Set the withdrawal amount to the user's lunch credit balance
        BigInteger withdrawalAmount = lunchCreditBalance;

        // Step 6: Create a withdrawal entity and save it
        Withdrawals withdrawal = new Withdrawals();
        withdrawal.setUser(user);
        withdrawal.setStatus("Confirmed");
        withdrawal.setAmount(withdrawalAmount);
        withdrawal.setCreated_at(LocalDateTime.now());
        Withdrawals savedWithdrawal = withdrawalRepository.save(withdrawal);

        // Step 7: Update the user's lunch credit balance & save it
        user.setLunch_credit_balance(BigInteger.ZERO);
        userRepository.save(user);

        // Step 8: Create a response DTO
        WithdrawalResponseDto withdrawalResponseDto = WithdrawalResponseDto.builder()
                .id(String.valueOf(savedWithdrawal.getId()))
                .userId(String.valueOf(savedWithdrawal.getUser().getId()))
                .status(savedWithdrawal.getStatus())
                .amount(savedWithdrawal.getAmount())
                .createdAt(savedWithdrawal.getCreated_at().toString())
                .build();

        // Step 9: Return the response with success status
        return new ApiResponseDto<>(withdrawalResponseDto, "Withdrawal request created successfully", HttpStatus.OK.value());
    }
}
