package org.hngxfreelunch.LiquidApplicationApi.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hngxfreelunch.LiquidApplicationApi.domain.model.User;

@AllArgsConstructor
@Builder
@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String phoneNumber;
    private String profilePic;

    public UserResponse(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.profilePic = user.getProfilePic();
    }
}
