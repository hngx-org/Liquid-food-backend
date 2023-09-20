package org.hngxfreelunch.LiquidApplicationApi.controller;

import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LunchRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponse;
import org.hngxfreelunch.LiquidApplicationApi.services.lunch.LunchService;
import org.hngxfreelunch.LiquidApplicationApi.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lunch/")
@CrossOrigin(origins = "*")
public class LunchController {

    @Autowired
    private LunchService lunchService;

    @Autowired
    private UserUtils userUtils;

    @PostMapping("send")
    public ResponseEntity<ApiResponse> sendLunch(@RequestBody LunchRequestDto lunchRequestDto) {

        return ResponseEntity.ok(new ApiResponse(lunchService.sendLunch(lunchRequestDto, userUtils.getLoggedInUser()), true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getLunch(@PathVariable long id) {
        return ResponseEntity.ok(new ApiResponse(lunchService.getLunch(id), true));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllLunch() {
        return ResponseEntity.ok(new ApiResponse(lunchService.getAllLunch(userUtils.getLoggedInUser().getId()), true));
    }

}
