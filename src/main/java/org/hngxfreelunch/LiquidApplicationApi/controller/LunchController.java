package org.hngxfreelunch.LiquidApplicationApi.controller;

import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LunchRequestDto;
import org.hngxfreelunch.LiquidApplicationApi.data.dtos.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/lunch/")
@CrossOrigin(origins = "*")
public class LunchController {

    @PostMapping("send")
    public ResponseEntity<ApiResponse> sendLunch(@RequestBody LunchRequestDto lunchRequestDto) {
        return ResponseEntity.ok(new ApiResponse(null, true));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getLunch(@PathVariable long id) {
        return ResponseEntity.ok(new ApiResponse(null, true));
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllLunch(){
        return ResponseEntity.ok(new ApiResponse(null, true));
    }
}
