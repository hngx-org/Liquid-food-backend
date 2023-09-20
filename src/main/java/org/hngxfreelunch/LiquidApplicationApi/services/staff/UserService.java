package org.hngxfreelunch.LiquidApplicationApi.services.staff;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;

public interface UserService {
    // TODO: CREATE STAFF
    // TODO: GET STAFF DETAILS BY NAME OR EMAIL
    // TODO: ADD BANK DETAILS
    public ApiResponseDto addBankDetails(BankRequestDto bankRequestDto);

    // TODO: GET ALL USERS
    public ApiResponseDto getAllUsers();
}
