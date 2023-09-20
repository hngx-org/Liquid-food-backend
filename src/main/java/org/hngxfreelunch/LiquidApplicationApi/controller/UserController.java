package org.hngxfreelunch.LiquidApplicationApi.controller;


import jakarta.validation.Valid;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;

import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
public class UserController {

    @GetMapping("user/profile")
    public ResponseEntity<ApiResponse> getProfile(){
        return ResponseEntity.ok(new ApiResponse(null,true));
    }

    @PostMapping("user/bank")
    public ResponseEntity<ApiResponse> addBankAccount(@Valid @RequestBody BankRequestDto bankRequestDto){
        return ResponseEntity.ok(new ApiResponse(null,true));
    }

    @GetMapping("users")
    public ResponseEntity<ApiResponse> getAllUsers(){
    return ResponseEntity.ok(new ApiResponse(null,true));
    }

    @GetMapping("/search/{nameOrEmail}")
    public ResponseEntity<ApiResponse> searchForUser(@PathVariable String nameOrEmail){
        return ResponseEntity.ok(new ApiResponse(null, true));
    }


}
