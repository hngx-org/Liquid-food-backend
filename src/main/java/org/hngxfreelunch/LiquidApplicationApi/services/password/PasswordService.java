package org.hngxfreelunch.LiquidApplicationApi.services.password;

import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.PasswordResetDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ResetResponse;
import org.springframework.stereotype.Service;

@Service
public interface PasswordService {
    ApiResponseDto<ResetResponse> forgotPassword(String email);

    ApiResponseDto<String> resetPassword(PasswordResetDto passwordResetDto);
}
