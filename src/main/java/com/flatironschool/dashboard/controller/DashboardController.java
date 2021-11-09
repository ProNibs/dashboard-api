package com.flatironschool.dashboard.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.flatironschool.dashboard.model.Condenser;
import com.flatironschool.dashboard.service.DashboardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController()
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "http://localhost:3000")
public class DashboardController {
    @Autowired
    DashboardService dashboardService;

    @GetMapping("/health")
    public String healthCheck() {
        return "OK";
    }

    @GetMapping("/ready")
    public ResponseEntity readyCheck() {
        try {
            // Ensure data is pre-populated correctly
            if (getAllCondensers().iterator().hasNext()) {
                return new ResponseEntity(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
        }
        // Not connected to DB yet, return 503
        return new ResponseEntity(HttpStatus.SERVICE_UNAVAILABLE);
    }

    @GetMapping("/condenser")
    public Iterable<Condenser> getAllCondensers() {
        return dashboardService.getAllCondensers();
    }

    @PostMapping("/condenser")
    public Condenser createCondenser(@RequestBody Condenser condenser) {
        return this.dashboardService.addCondenser(condenser);
    }
}
