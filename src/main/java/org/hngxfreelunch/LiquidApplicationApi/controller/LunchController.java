package org.hngxfreelunch.LiquidApplicationApi.controller;

import org.hngxfreelunch.LiquidApplicationApi.data.dtos.payload.LunchRequestDto;
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
    public ResponseEntity<?> sendLunch(@RequestBody LunchRequestDto lunchRequestDto) {
        return ResponseEntity.ok(lunchService.sendLunch(lunchRequestDto, userUtils.getLoggedInUser()));
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getLunch(@PathVariable long id) {
        return ResponseEntity.ok(lunchService.getLunch(id));
    }

    @GetMapping("all")
    public ResponseEntity<?> getAllLunchForAStaff() {
        return ResponseEntity.ok(lunchService.getAllLunch(userUtils.getLoggedInUser().getId()));
    }

}
