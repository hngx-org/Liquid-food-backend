package org.hngxfreelunch.LiquidApplicationApi.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.ChangePasswordDto;
import org.hngxfreelunch.LiquidApplicationApi.services.organization.OrganizationService;
import org.hngxfreelunch.LiquidApplicationApi.services.password.PasswordService;
import org.hngxfreelunch.LiquidApplicationApi.services.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final OrganizationService organizationService;
    private final PasswordService passwordService;

    @Operation(summary = "Get the logged in User",
            description = "Returns an ApiResponse Response entity containing the user's details")
    @GetMapping("profile")
    public ResponseEntity<?> getProfile(){
        return ResponseEntity.ok(userService.getLoggedInUser());
    }

    @Operation(summary = "Update User's bank details",
            description = "Returns an ApiResponse Response entity containing the user's bank details")
    @PatchMapping("bank")
    public ResponseEntity<?> addBankAccount(@Valid @RequestBody BankRequestDto bankRequestDto){
        return ResponseEntity.ok(userService.addBankDetails(bankRequestDto));
    }

    @Operation(summary = "Search for users within an organization",
            description = "You can search by first name, last name, or both names, or email")
    @GetMapping("all-users")
    public ResponseEntity<?> searchForUsers(@RequestParam("keyword") String keyword){
        return ResponseEntity.ok(userService.searchByNameOrEmail(keyword));
    }

    @Operation(summary = "Get all staff in this User's organization",
            description = "Returns an ApiResponse Response entity containing a list of the user's in this org")
    @GetMapping("all")
    public ResponseEntity<?> getAllUsers(){
    return ResponseEntity.ok(organizationService.getAllStaffInOrganization());
    }

    @Operation(summary = "Get the logged in User's bank details",
            description = "Returns an ApiResponse Response entity containing the user's bank details")
    @GetMapping("bank-details")
    public ResponseEntity<?> getUserBankDetails(){
        return ResponseEntity.ok(userService.getUserBankDetails());
    }


    @Operation(summary = "Update A Particular User's profile picture",
            description = "Returns an ApiResponse Response entity containing the operation details")
    @PatchMapping(value = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadProfilePicture(@RequestParam(value = "file") MultipartFile file) {
        try {
            String response = userService.uploadProfileImage(file);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @Operation(summary = "A logged in user tries to change his password from the app")
    @PatchMapping("change-password")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordDto changePasswordDto){
        return ResponseEntity.ok(passwordService.changePassword(changePasswordDto));
    }

    @Operation(summary = "Get Admin Details for Organization")
    @GetMapping("get-admin")
    public ResponseEntity<?> getAdmin(){
        return ResponseEntity.ok(userService.getAdminDetails());
    }

}
