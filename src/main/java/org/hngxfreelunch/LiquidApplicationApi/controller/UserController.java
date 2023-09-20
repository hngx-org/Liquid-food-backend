package org.hngxfreelunch.LiquidApplicationApi.controller;


import jakarta.validation.Valid;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;

import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponse;
import org.hngxfreelunch.LiquidApplicationApi.services.organization.OrganizationService;
import org.hngxfreelunch.LiquidApplicationApi.services.user.UserService;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserUtils userUtils;
    @GetMapping("user/profile")
    public ResponseEntity<ApiResponse> getProfile(){
        return ResponseEntity.ok(new ApiResponse(userService.getUserByName(userUtils.getLoggedInUser().getFirst_name()),true));
    }

    @PostMapping("user/bank")
    public ResponseEntity<ApiResponse> addBankAccount(@Valid @RequestBody BankRequestDto bankRequestDto){
        return ResponseEntity.ok(new ApiResponse(userService.addBankDetails(bankRequestDto),true));
    }

    @GetMapping("users")
    public ResponseEntity<ApiResponse> getAllUsers(){
    return ResponseEntity.ok(new ApiResponse(organizationService.getAllStaffInOrganization(),true));
    }

    @GetMapping("/search/{nameOrEmail}")
    public ResponseEntity<ApiResponse> searchForUser(@PathVariable String nameOrEmail){
        return ResponseEntity.ok(new ApiResponse(null, true));
    }


}
