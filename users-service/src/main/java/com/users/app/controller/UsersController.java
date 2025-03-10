package com.users.app.controller;

import com.users.app.dto.*;
import com.users.app.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/save")
    public ResponseEntity<UsersResponse> saveUser(@RequestBody UsersRequest request) {
        UsersResponse response = usersService.save(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponse> getUserById(@PathVariable int id) {
        UsersResponse response = usersService.getUserById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<UsersResponse> getUserByPhone(@PathVariable String phone) {
        UsersResponse response = usersService.getUserByPhone(phone);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersResponse> updateUser(@PathVariable int id, @RequestBody UsersRequest request) {
        UsersResponse response = usersService.updateUser(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/check-token")
    public ResponseEntity<Boolean> checkToken(@RequestParam String token) {
        Boolean isValid = usersService.checkToken(token);
        return ResponseEntity.ok(isValid);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest request) {
        RegisterResponse response = usersService.register(request);
        return ResponseEntity.ok(response);
    }
}

