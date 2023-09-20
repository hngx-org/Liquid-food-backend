package org.hngxfreelunch.LiquidApplicationApi.services.user;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.StaffSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.UpdateStaffRequest;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;

public interface UserService {
    // TODO: CREATE STAFF
    ApiResponseDto<?> createStaff(StaffSignupDto signupRequest);
    // TODO: GET STAFF DETAILS BY NAME OR EMAIL
    User getStaffByNameOrEmail(String name, String email);

    // TODO: ADD BANK DETAILS
    // TODO: GET ALL USERS
}
