package org.hngxfreelunch.LiquidApplicationApi.services.staff;


import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.StaffDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Staff;

public interface StaffService {
    // TODO: ADD NEW STAFF TO TEAM
    public ApiResponseDto addStaff(StaffDto StaffRequestDto);
    // TODO: GET STAFF DETAILS
    public Staff getStaff(Long id);
    // TODO: UPDATE STAFF DETAILS
    public ApiResponseDto updateStaff(Long id, StaffDto updatedStaffRequestDto);
}
