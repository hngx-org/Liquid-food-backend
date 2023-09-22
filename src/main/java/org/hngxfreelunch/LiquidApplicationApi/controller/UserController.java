package org.hngxfreelunch.LiquidApplicationApi.controller;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.BankRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.services.organization.OrganizationService;
import org.hngxfreelunch.LiquidApplicationApi.services.user.UserService;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserUtils userUtils;

    @GetMapping("profile")
    public ResponseEntity<?> getProfile(){
        return ResponseEntity.ok(userService.getUserByEmail(userUtils.getLoggedInUser().getEmail()));
    }

    @PutMapping("bank")
    public ResponseEntity<?> addBankAccount(@Valid @RequestBody BankRequestDto bankRequestDto){
        return ResponseEntity.ok(userService.addBankDetails(bankRequestDto));
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllUsers(){
    return ResponseEntity.ok(organizationService.getAllStaffInOrganization());
    }


    @Operation(summary = "Update A Particular User's profile picture",
            description = "Returns an ApiResponse Response entity containing the operation details")
    @PostMapping(value = "/upload-image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadProfilePicture(@RequestParam(value = "file") MultipartFile file) {
        try {
            String response = userService.uploadProfileImage(file);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (Exception exception){
            return ResponseEntity.badRequest().body(exception.getMessage());
        }
    }

    @Operation(summary = "Search for user by name or email")
    @GetMapping("search/{nameOrEmail}")
    public ResponseEntity<?> searchForUser(@PathVariable String nameOrEmail){
        return ResponseEntity.ok(userService.getUserByEmail(nameOrEmail));
    }

    @Operation(summary = "Search for user by first name or last name",
    description = "Search for user by firstname or lastname and return a List of users with that name.")
    @GetMapping("search/{firstNameOrLastName}")
    public ResponseEntity<?> searchForUserByName(@PathVariable String firstNameOrLastName){
        return ResponseEntity.ok(userService.getUsersByName(firstNameOrLastName));
    }


}
