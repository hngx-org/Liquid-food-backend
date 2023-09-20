package org.hngxfreelunch.LiquidApplicationApi.services.staff;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.springframework.security.core.context.SecurityContext;

public interface UserService {
    // TODO: CREATE STAFF
    // TODO: GET STAFF DETAILS BY NAME OR EMAIL
    // TODO: ADD BANK DETAILS
    public ApiResponseDto addBankDetails(Long id, BankRequestDto bankRequestDto);

}
