package org.hngxfreelunch.LiquidApplicationApi.services.withdraw;

import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.WithdrawalRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.WithdrawalResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Withdrawals;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.UserRepository;
import org.hngxfreelunch.LiquidApplicationApi.data.repositories.WithdrawalRepository;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WithdrawalRequestServiceImplementation implements WithdrawalService {

    private final WithdrawalRepository withdrawalRepository;

    private final UserRepository userRepository;

    public WithdrawalResponseDto processWithdrawalRequest(WithdrawalRequestDto withdrawalRequestDto, Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        BigInteger withdrawalAmount = withdrawalRequestDto.getAmount();
        BigInteger userBalance = Optional.ofNullable(user.getLunch_credit_balance())
                .map(BigInteger::new)
                .orElse(BigInteger.ZERO);

        if (userBalance.compareTo(withdrawalAmount) < 0) {
            return WithdrawalResponseDto.builder()
                    .apiResponseDto(new ApiResponseDto<>("Insufficient lunch credits", 400))
                    .build();
        }

        // Create a withdrawal record
        Withdrawals withdrawal = new Withdrawals();
        withdrawal.setUser(user);
        withdrawal.setStatus("Pending"); // You can set the initial status as per your requirements
        withdrawal.setAmount(withdrawalAmount);
        withdrawal.setCreated_at(LocalDateTime.now());

        withdrawalRepository.save(withdrawal);

        // Update the user's lunch credit balance
        user.setLunch_credit_balance(userBalance.subtract(withdrawalAmount).toString());
        userRepository.save(user);

        return WithdrawalResponseDto.builder()
                .apiResponseDto(new ApiResponseDto<>("Withdrawal request created successfully", 200))
                .id(String.valueOf(withdrawal.getId()))
                .userId(String.valueOf(user.getId()))
                .status(withdrawal.getStatus())
                .amount(withdrawal.getAmount())
                .createdAt(withdrawal.getCreated_at().toString())
                .build();
    }

//        // Find the user by userId
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new EntityNotFoundException("User not found"));
//
//        // Validate that the user has sufficient lunch credits
//        BigInteger withdrawalAmount = withdrawalRequestDto.getAmount();
//        BigInteger userLunchCredit = new BigInteger(user.getLunch_credit_balance(), 10);
//
//        if (userLunchCredit.compareTo(withdrawalAmount) < 0) {
//            throw new RuntimeException("Insufficient lunch credits for withdrawal");
//        }
//
//        // Deduct the withdrawal amount from the user's lunch credits
//
//        BigInteger newLunchCreditBalance = userLunchCredit.subtract(withdrawalAmount);
//        user.setLunch_credit_balance(newLunchCreditBalance.toString());
//        userRepository.save(user);
//
//        // Create a withdrawal entity and save it
//        Withdrawals withdrawal = new Withdrawals();
//        withdrawal.setUser(user);
//        withdrawal.setStatus("Pending"); // You can set the initial status here
//        withdrawal.setAmount(withdrawalAmount);
//        withdrawal.setCreated_at(LocalDateTime.now());
//        withdrawalRepository.save(withdrawal);
//
//        // return a response
//
//        return WithdrawalResponseDto.builder()
//                .apiResponseDto(new ApiResponseDto<>("Withdrawal request created successfully", 200))
//                .id(String.valueOf(withdrawal.getId()))
//                .userId(String.valueOf(user.getId()))
//                .status(withdrawal.getStatus())
//                .amount(withdrawal.getAmount())
//                .createdAt(withdrawal.getCreated_at().toString())
//                .build();
//
//        responseDto.setId(withdrawal.getId().toString());
//        responseDto.setUserId(user.getId().toString());
//        responseDto.setStatus(withdrawal.getStatus());
//        responseDto.setAmount(withdrawal.getAmount());
//        responseDto.setCreatedAt(withdrawal.getCreated_at().toString());
//
////        return ApiResponseDto.<WithdrawalResponseDto>builder()
////                .data(responseDto)
////                .message("Withdrawal successful")
////                .statusCode(HttpStatus.OK.value())
////                .build();
////    }

    }

}
