package org.hngxfreelunch.LiquidApplicationApi.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LunchRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.LunchResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.services.lunch.LunchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/lunch/")
@CrossOrigin(origins = "*")
public class LunchController {
    private final LunchService lunchService;

    @PostMapping("send")
    public ResponseEntity<ApiResponse> sendLunch(@RequestBody LunchRequestDto lunchRequestDto, HttpServletRequest request) {
        List<LunchResponseDto> responseDto= lunchService.sendLunch(lunchRequestDto,request);
        return ResponseEntity.ok(new ApiResponse(responseDto, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getLunch(@PathVariable long id) {
        LunchResponseDto responseDto= lunchService.getLunch(id);
        return ResponseEntity.ok(new ApiResponse(null, true));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllLunch(Long user_id){
        List<LunchResponseDto> responseDtoList=lunchService.getAllLunch(user_id);
        return ResponseEntity.ok(new ApiResponse(responseDtoList, true));
    }
}
