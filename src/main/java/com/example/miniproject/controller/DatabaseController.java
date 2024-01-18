package com.example.miniproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DatabaseController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/check-db-connection")
    public String checkDbConnection() {
        try {
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return "Connected to the database!";
        } catch (Exception e) {
            return "Failed to connect to the database. Error: " + e.getMessage();
        }
    }
}

