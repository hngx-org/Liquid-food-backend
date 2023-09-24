package org.hngxfreelunch.LiquidApplicationApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LunchRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.services.lunch.LunchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lunch")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LunchController {

    private final LunchService lunchService;


    @Operation(summary = "Send lunch to staff")
    @PostMapping("/send/{receiverId}")
    public ResponseEntity<?> sendLunch(
            @Parameter(description = "Quantity of lunch & Staff Id are required while Note is optional")
            @RequestBody LunchRequestDto lunchRequestDto, @PathVariable Long receiverId) {
        return ResponseEntity.ok(lunchService.sendLunch(receiverId,lunchRequestDto));
    }

    @Operation(summary = "Staff attempts to fetch lunch by id")
    @GetMapping("/{lunchId}")
    public ResponseEntity<?> getLunch(
            @Parameter(required = true, description = "Lunch Id") @PathVariable Long lunchId ) {
        return ResponseEntity.ok(lunchService.getLunch(lunchId));
    }

    @Operation(summary = "Staff attempts to fetch all his lunch history")
    @GetMapping("/all")
    public ResponseEntity<?> getAllLunchByStaff(){
        return ResponseEntity.ok(lunchService.getAllUserLunches());
    }

    @Operation(summary = "Staff attempts to fetch all his pending lunch history")
    @GetMapping("/pending")
    public ResponseEntity<?> getAllPendingLunchByStaff(){
        return ResponseEntity.ok(lunchService.getAllPendingLunches());
    }

    @Operation(summary = "Staff attempts to fetch all his redeemed lunch history")
    @GetMapping("/redeemed")
    public ResponseEntity<?> getAllRedeemedLunchByStaff(){
        return ResponseEntity.ok(lunchService.getAllRedeemedLunches());
    }

    @Operation(summary = "Staff attempts to fetch all lunches sent by him")
    @GetMapping("/sent")
    public ResponseEntity<?> getAllSentLunchByStaff(){
        return ResponseEntity.ok(lunchService.getAllSentLunchesByUser());
    }

    @Operation(summary = "Staff attempts to fetch all lunches received by him")
    @GetMapping("/received")
    public ResponseEntity<?> getAllReceivedLunchByStaff(){
        return ResponseEntity.ok(lunchService.getAllReceivedLunchesByUser());
    }

    @Operation(summary = "Admin attempts to fetch all organization lunch history")
    @GetMapping("/organization")
    public ResponseEntity<?> getAllOrganizationLunch(){
        return ResponseEntity.ok(lunchService.getAllOrganizationLunches());
    }

    @Operation(summary = "Staff attempts to redeem lunch")
    @PatchMapping("/redeem-lunch/{lunchId}")
    public ResponseEntity<?> redeemLunch(@PathVariable Long lunchId){
        return ResponseEntity.ok(lunchService.redeemLunch(lunchId));
    }

    @Operation(summary = "Staff attempts to cancel lunch")
    @PatchMapping("/cancel-lunch/{lunchId}")
    public ResponseEntity<?> cancelLunch(@PathVariable Long lunchId){
        return ResponseEntity.ok(lunchService.cancelLunch(lunchId));
    }
}
