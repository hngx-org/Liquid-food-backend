package org.hngxfreelunch.LiquidApplicationApi.controller;

import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.UserSignupDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.services.user.UserService;
import org.hngxfreelunch.LiquidApplicationApi.utils.JacksonConfig;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@RunWith(SpringRunner.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LocalValidatorFactoryBean validator;

    @Autowired
    private JacksonConfig objectMapper;

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
    public void testGetProfileEndpoint() throws Exception {
        mockMvc.perform(get("/api/user/profile")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isEmpty());
    }


    @Test
    public void testSearchForUser() throws Exception{
        mockMvc.perform(get("/api/search/{nameOrEmail}", "angelbeta@hotmail.com")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isEmpty());
    }

//    @Test
//    public void testAddBankAccountEndpoint() throws Exception {
//        BankRequestDto validBankRequest = new BankRequestDto();
//        validBankRequest.setBankNumber("1234567890");
//        validBankRequest.setBankName("Test Bank");
//
//        mockMvc.perform(post("/api/user/bank")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonRequest))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.success").value(true))
//                .andExpect(jsonPath("$.data").isEmpty());
//    }

//    @Test
//    public void createUser() throws Exception{
//        UserSignupDto newStaffTest = new UserSignupDto();
//        newStaffTest.setFirstName("Ifeanyichukwu");
//        newStaffTest.setLastName("Obeta");
//        newStaffTest.setEmail("angeloobeta@hotmail.com");
//        newStaffTest.setPhoneNumber("8118644930");
//        newStaffTest.setPassword(passwordEncoder.encode("7682$jahs!0jahwt"));
//
//
//        Mockito.when(userService.createUser(newStaffTest)).thenReturn(new ApiResponseDto<>(userSignupDto, "Staff created successfully", HttpStatus.CREATED.value()));
//
//        mockMvc.perform(post("/api/department")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content("{\n" +
//                        "\t\"email\":\"angela@hotmail.com\", \n" +
//                        "\t \"password\": \"7682$jahs!0jahwt\", \n" +
//                        "\t \"firstName\":\"ifeanyichukwu\", \n" +
//                        "\t \"lastName\":\"Obeta\", \n" +
//                        "\t \"phoneNumber\":\"8118644930\" \n" +
//                        "}")).andExpect(status().isOk());
//    }


//    @Test
//    public void findUserByName() throws Exception{
//        String staffName = "ifeanyichukwu";
//
//        Mockito.when(userService.getUserByName("Ifeanyichukwu")).thenReturn(new ApiResponseDto<>(staffName, "Staff created successfully", HttpStatus.CREATED.value()));
//
//        mockMvc.perform(get("/search/${staffName}")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
////                .andExpect(jsonPath("$.departmentName")
////                        .value(department.getDepartmentName()));
//    }
}
