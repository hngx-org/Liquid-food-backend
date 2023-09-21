package org.hngxfreelunch.LiquidApplicationApi.controller;


import jakarta.validation.Valid;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;
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
    public ResponseEntity<?> getProfile(){
        return ResponseEntity.ok(userService.getUserByName(userUtils.getLoggedInUser().getFirstName()));
    }

    @PostMapping("user/bank")
    public ResponseEntity<?> addBankAccount(@Valid @RequestBody BankRequestDto bankRequestDto){
        return ResponseEntity.ok(userService.addBankDetails(bankRequestDto));
    }

    @GetMapping("users")
    public ResponseEntity<?> getAllUsers(){
    return ResponseEntity.ok(organizationService.getAllStaffInOrganization());
    }

    @GetMapping("/search/{nameOrEmail}")
    public ResponseEntity<?> searchForUser(@PathVariable String nameOrEmail){
        return ResponseEntity.ok(null);
    }


}
