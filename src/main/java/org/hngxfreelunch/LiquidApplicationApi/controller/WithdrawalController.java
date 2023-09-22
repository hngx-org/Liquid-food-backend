package org.hngxfreelunch.LiquidApplicationApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.WithdrawalRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.services.withdraw.WithdrawalService;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/withdrawal/")
@CrossOrigin(origins = "*")
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;

    @Autowired
    private UserUtils userUtils;

    @PostMapping("request")
    @Operation(summary = "User attempts to withdraw their credits")
    public ResponseEntity<?> makeANewWithDraw(@RequestBody
            @Parameter(required = true) WithdrawalRequestDto withdrawalRequestDto){
        return ResponseEntity.ok(withdrawalService.processWithdrawalRequest(withdrawalRequestDto,userUtils.getLoggedInUser().getId()));
    }
}
