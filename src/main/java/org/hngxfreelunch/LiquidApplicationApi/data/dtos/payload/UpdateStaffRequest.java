package org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload;

import jakarta.validation.constraints.NotNull;

public class UpdateStaffRequest {
    @NotNull(message = "Enter first name")
    private  String firstName;
    private String lastName;
    private String profilePicture;
    @NotNull(message = "Enter your email")
    private String email;
    private int phoneNumber;
}
