package org.hngxfreelunch.LiquidApplicationApi.data.dtos.response;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.UsersDtos;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.Users;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponseDto {

    private ApiResponseDto apiResponseDto;
    private Users users;
}
