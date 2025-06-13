package com.example.mcpserver.controller;

import com.example.mcpserver.service.FreightService;
// Import DocumentNotFoundException from the service package
import com.example.mcpserver.service.DocumentNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/freight")
public class FreightController {

    private final FreightService freightService;

    @Autowired
    public FreightController(FreightService freightService) {
        this.freightService = freightService;
    }

    @GetMapping("/{documentNumber}/eta")
    public ResponseEntity<LocalDateTime> getEstimatedArrivalTime(@PathVariable String documentNumber) {
        LocalDateTime eta = freightService.getEstimatedArrivalTime(documentNumber);
        return ResponseEntity.ok(eta);
    }
}
