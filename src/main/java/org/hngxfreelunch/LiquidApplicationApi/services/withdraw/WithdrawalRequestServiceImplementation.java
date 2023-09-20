package org.hngxfreelunch.LiquidApplicationApi.services.withdraw;

import jakarta.persistence.EntityNotFoundException;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.WithdrawalRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.WithdrawalResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Withdrawals;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.WithdrawalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Service
public class WithdrawalRequestServiceImplementation implements WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;

    private final UserRepository userRepository;

    @Autowired
    public WithdrawalRequestServiceImplementation(WithdrawalRepository withdrawalRepository, UserRepository userRepository) {
        this.withdrawalRepository = withdrawalRepository;
        this.userRepository = userRepository;
    }

    public WithdrawalResponseDto processWithdrawalRequest(WithdrawalRequestDto withdrawalRequestDto, Long userId) {
        // Find the user by userId
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Validate that the user has sufficient lunch credits
        BigInteger userLunchCredit = new BigInteger(user.getLunch_credit_balance());
        BigInteger withdrawalAmount = withdrawalRequestDto.getAmount();
        if (userLunchCredit.compareTo(withdrawalAmount) < 0) {
            throw new RuntimeException("Insufficient lunch credits for withdrawal");
        }

        // Deduct the withdrawal amount from the user's lunch credits
        userLunchCredit = userLunchCredit.subtract(withdrawalAmount);
        user.setLunch_credit_balance(userLunchCredit.toString());
        userRepository.save(user);

        // Create a withdrawal entity and save it
        Withdrawals withdrawal = new Withdrawals();
        withdrawal.setUser(user);
        withdrawal.setStatus("Pending"); // You can set the initial status here
        withdrawal.setAmount(withdrawalAmount);
        withdrawal.setCreated_at(LocalDateTime.now());
        withdrawalRepository.save(withdrawal);

        // return a response
        ApiResponseDto apiResponseDto = ApiResponseDto
                .builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successful")
                .build();

        return WithdrawalResponseDto
                .builder()
                .apiResponseDto(apiResponseDto)
                .status("Withdrawal request created successfully with ID:" + withdrawal.getId())
                .build();
    }
}
