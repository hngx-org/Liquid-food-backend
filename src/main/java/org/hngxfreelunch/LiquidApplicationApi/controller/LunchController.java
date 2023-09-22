package org.hngxfreelunch.LiquidApplicationApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LunchRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LunchResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.services.lunch.LunchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lunch/")
@CrossOrigin(origins = "*")
public class LunchController {
    private final LunchService lunchService;


    @Operation(summary = "Send lunch to staff")
    @PostMapping("send")
    public ResponseEntity<ApiResponse> sendLunch(
            @Parameter(description = "Quantity of lunch & Staff Id are required while Note is optional")
            @RequestBody LunchRequestDto lunchRequestDto, HttpServletRequest request) {
        List<LunchResponseDto> responseDto= lunchService.sendLunch(lunchRequestDto,request);
        return ResponseEntity.ok(new ApiResponse(responseDto, true));
    }

    @Operation(summary = "Staff attempts to fetch lunch by id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getLunch(
            @Parameter(required = true, description = "Lunch Id") @PathVariable long id ) {
        LunchResponseDto responseDto= lunchService.getLunch(id);
        return ResponseEntity.ok(new ApiResponse(responseDto, true));
    }

    @Operation(summary = "Staff attempts to fetch all lunch history")
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllLunch(){
        List<LunchResponseDto> responseDtoList=lunchService.getAllLunch();
        return ResponseEntity.ok(new ApiResponse(responseDtoList, true));
    }
}
