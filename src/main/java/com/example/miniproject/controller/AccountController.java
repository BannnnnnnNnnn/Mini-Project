package com.example.miniproject.controller;

import com.example.miniproject.DTO.AccountDTO;
import com.example.miniproject.request.LoginRequest;
import com.example.miniproject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<AccountDTO> login(@RequestBody LoginRequest loginRequest) {
        AccountDTO accountDTO = accountService.Logging(loginRequest.getUsername(), loginRequest.getPassword());

        if (accountDTO != null) {
            return ResponseEntity.ok(accountDTO);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<AccountDTO>> getall() {
        List<AccountDTO> listAccount = accountService.getAll();
        return ResponseEntity.ok(listAccount);
    }

    @PostMapping("/create")
    public ResponseEntity<String> insert(@RequestBody AccountDTO accountDTO) {
        if (accountService.createAccount(accountDTO)) {
            return ResponseEntity.ok("Account created successfully!");
        } else {
            return ResponseEntity.badRequest().body("Username already exists!");
        }
    }

    @PutMapping("/update/{username}")
    public ResponseEntity<String> update(@PathVariable String username, @RequestBody AccountDTO updatedAccountDTO) {
        if (accountService.updateAccount(username, updatedAccountDTO)) {
            return ResponseEntity.ok("Account updated successfully!");
        } else {
            return ResponseEntity.badRequest().body("Account not found or update failed!");
        }
    }

    @DeleteMapping("/delete/{username}")
    public ResponseEntity<String> deleteAccount(@PathVariable String username) {
        if (accountService.deleteAccount(username)) {
            return ResponseEntity.ok("Account deleted successfully!");
        } else {
            return ResponseEntity.badRequest().body("Account not found or already deleted!");
        }
    }
}
