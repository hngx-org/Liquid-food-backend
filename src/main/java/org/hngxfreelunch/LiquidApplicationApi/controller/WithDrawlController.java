package org.hngxfreelunch.LiquidApplicationApi.controller;

import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.WithdrawalRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponse;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.WithdrawalResponseDto;
import org.hngxfreelunch.LiquidApplicationApi.services.withdraw.WithdrawalService;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/withdrawal/")
@CrossOrigin(origins = "*")
public class WithDrawlController {

    @Autowired
    private WithdrawalService withdrawalService;

    @Autowired
    private UserUtils userUtils;
    @PostMapping("request")
    public ResponseEntity<?> makeANewWithDraw(@RequestBody WithdrawalRequestDto withdrawalRequestDto){
        return ResponseEntity.ok(withdrawalService.processWithdrawalRequest(withdrawalRequestDto,userUtils.getLoggedInUser().getId()));
    }


}
