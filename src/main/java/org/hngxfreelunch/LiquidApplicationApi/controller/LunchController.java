package org.hngxfreelunch.LiquidApplicationApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LunchRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LunchResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.data.entities.User;
import org.hngxfreelunch.LiquidApplicationApi.services.lunch.LunchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lunch")
@CrossOrigin(origins = "*")
public class LunchController {


    @Autowired
    private LunchService lunchService;

    @Autowired
    private UserUtils userUtils;


    @Operation(summary = "Send lunch to staff")
    @PostMapping("/send")
    public ResponseEntity<?> sendLunch(
            @Parameter(description = "Quantity of lunch & Staff Id are required while Note is optional")
            @RequestBody LunchRequestDto lunchRequestDto) {
        System.out.println("1");
        List<LunchResponseDto> responseDto= lunchService.sendLunch(lunchRequestDto);
        System.out.println("end");
        return ResponseEntity.ok(new ApiResponseDto<>(responseDto,"Sent Lunch to Staff", HttpStatus.OK.value()));
    }

    @Operation(summary = "Staff attempts to fetch lunch by id")
    @GetMapping("/{id}")
    public ResponseEntity<?> getLunch(
            @Parameter(required = true, description = "Lunch Id") @PathVariable Long id ) {
        LunchResponseDto responseDto= lunchService.getLunch(id);
        return ResponseEntity.ok(new ApiResponseDto<>(responseDto,"Fetched Lunch", HttpStatus.OK.value()));
    }

    @Operation(summary = "Staff attempts to fetch all lunch history")
    @GetMapping("/all")
    public ResponseEntity<?> getAllLunch(){
        List<LunchResponseDto> responseDtoList=lunchService.getAllLunch();
        return ResponseEntity.ok(new ApiResponseDto(responseDtoList,"Staff Lunch History" , HttpStatus.OK.value()));
    }

    @Operation(summary = "Staff attempts to get lunch credit balance")
    @GetMapping("/balance")
    public ResponseEntity<?> getLunchBalance(){
        User loggedInUser = userUtils.getLoggedInUser();
        return ResponseEntity.ok(new ApiResponseDto(loggedInUser.getLunchCreditBalance(),"User's Lunch Credits", HttpStatus.OK.value()));
    }
}
