package org.hngxfreelunch.LiquidApplicationApi.controller;

import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.WithdrawalResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/withdrawal/")
@CrossOrigin(origins = "*")
public class WithDrawlController {

    @PostMapping("request")
    public ResponseEntity<ApiResponse> makeANewWithDraw(@RequestBody WithdrawalResponseDto withdrawalResponseDto){
        return ResponseEntity.ok(new ApiResponse(null, true));
    }


}
