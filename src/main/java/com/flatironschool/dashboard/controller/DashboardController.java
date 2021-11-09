package com.flatironschool.dashboard.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.flatironschool.dashboard.model.Condenser;
import com.flatironschool.dashboard.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/")
    public String healthCheck() {
        return "OK";
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
