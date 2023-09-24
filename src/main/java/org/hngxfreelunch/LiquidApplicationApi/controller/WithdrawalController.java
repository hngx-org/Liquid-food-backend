package org.hngxfreelunch.LiquidApplicationApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.WithdrawalRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.services.withdraw.WithdrawalService;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/withdrawal/")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class WithdrawalController {

    private final WithdrawalService withdrawalService;

    private final UserUtils userUtils;

    @PostMapping("request")
    @Operation(summary = "User attempts to withdraw their credits")
    public ResponseEntity<?> makeANewWithDraw(@RequestBody WithdrawalRequestDto withdrawalRequestDto){
        return ResponseEntity.ok(withdrawalService.processWithdrawalRequest(withdrawalRequestDto));
    }

}
