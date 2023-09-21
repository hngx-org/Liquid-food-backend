package org.hngxfreelunch.LiquidApplicationApi.controller;

import org.hngxfreelunch.LiquidApplicationApi.data.dtos.UserDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.UserSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.services.user.UserService;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockmvc;

    @MockBean
    private UserService userService;

    private PasswordEncoder passwordEncoder;

    private User user;
    private UserSignupDto userSignupDto;
    @MockBean
    private ApiResponseDto apiResponseDto;

    @BeforeEach
    void setUp() {
        UserSignupDto staffTest = new UserSignupDto();
            staffTest.setFirstName("Ifeanyichukwu");
            staffTest.setLastName("OBeta");
            staffTest.setEmail("angeloobeta@hotmail.com");
            staffTest.setPhoneNumber("8118644930");
            staffTest.setPassword(passwordEncoder.encode("7682$jahs!0jahwt"));
    }

    @Test
    public void createUser() throws Exception{
        UserSignupDto newStaffTest = new UserSignupDto();
        newStaffTest.setFirstName("Ifeanyichukwu");
        newStaffTest.setLastName("Obeta");
        newStaffTest.setEmail("angeloobeta@hotmail.com");
        newStaffTest.setPhoneNumber("8118644930");
        newStaffTest.setPassword(passwordEncoder.encode("7682$jahs!0jahwt"));


        Mockito.when(userService.createUser(newStaffTest)).thenReturn(new ApiResponseDto<>(userSignupDto, "Staff created successfully", HttpStatus.CREATED.value()));

        mockmvc.perform(post("/api/department")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "\t\"email\":\"angela@hotmail.com\", \n" +
                        "\t \"password\": \"7682$jahs!0jahwt\", \n" +
                        "\t \"firstName\":\"ifeanyichukwu\", \n" +
                        "\t \"lastName\":\"Obeta\", \n" +
                        "\t \"phoneNumber\":\"8118644930\" \n" +
                        "}")).andExpect(status().isOk());
    }


    @Test
    public void findUserByName() throws Exception{
        String staffName = "ifeanyichukwu";

        Mockito.when(userService.getUserByName("Ifeanyichukwu")).thenReturn(new ApiResponseDto<>(staffName, "Staff created successfully", HttpStatus.CREATED.value()));

        mockmvc.perform(get("/search/${staffName}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

//                .andExpect(jsonPath("$.departmentName")
//                        .value(department.getDepartmentName()));
    }
}
