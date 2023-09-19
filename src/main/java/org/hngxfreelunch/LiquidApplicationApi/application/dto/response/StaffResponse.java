package org.hngxfreelunch.LiquidApplicationApi.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hngxfreelunch.LiquidApplicationApi.domain.model.Staff;

@AllArgsConstructor
@Builder
@Data
public class StaffResponse {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String profilePic;

    public StaffResponse(Staff staff){
        this.id = staff.getId();
        this.name = staff.getName();
        this.email = staff.getEmail();
        this.phoneNumber = staff.getPhoneNumber();
        this.profilePic = staff.getProfilePic();
    }
}
