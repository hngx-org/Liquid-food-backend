package org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.AccountDetails;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Organization;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffDto {
    @NotBlank(message = "First Name is required")
    private String firstname;

    @NotBlank(message = "Last Name is required")
    private String lastname;

    @NotBlank(message = "Stack is required")
    private String stack;

    @NotBlank(message = "Lunch Credits is required")
    private Integer lunchCredits;

    @NotBlank(message = "Organization is required")
    private Organization organization;

    @NotBlank(message = "Account Details are required")
    private AccountDetails accountDetails;

    @NotBlank(message = "Sent lunches is required")
    private List<Lunch> lunches_sent;

    @NotBlank(message = "Received lunches is required")
    private List<Lunch> lunches_received;
}
