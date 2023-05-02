package com.apm.jacx.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/check")
public class HealthCheckController {

    @GetMapping
    private ResponseEntity<Void> check () {
        return ResponseEntity.ok().build();
    }
}
