package com.customer.app.customer_service.service.impl;

import com.customer.app.customer_service.client.dto.*;
import com.customer.app.customer_service.entity.Users;
import com.customer.app.customer_service.exception.ResourceNotFoundException;
import com.customer.app.customer_service.repository.UsersRepository;
import com.customer.app.customer_service.service.UsersService;
import com.customer.app.customer_service.service.AuditTrailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UsersService {

    @Autowired
    private UsersRepository userRepository;

    @Autowired
    private AuditTrailsService auditTrailsService;

    @Override
    public UsersResponse save(UsersRequest request) {
        Users user = Users.builder()
                .fullName(request.getFullName())
                .username(request.getEmail())
                .phone(request.getPhone())
                .password(request.getPassword())
                .build();

        Users savedUser  = userRepository.save(user);

        // Create an audit trail for user creation
        AuditTrailsRequest auditRequest = new AuditTrailsRequest();
        auditRequest.setAction("CREATE");
        auditRequest.setDescription("User  created with ID: " + savedUser .getId());
        auditRequest.setRequest(request.toString());
        auditRequest.setResponse(savedUser .toString());
        auditTrailsService.insertAuditTrails(auditRequest);

        return UsersResponse.builder()
                .id(savedUser .getId())
                .fullName(savedUser .getFullName())
                .username(savedUser.getUsername())
                .phone(savedUser .getPhone())
                .build();
    }



    @Override
    public UsersResponse getUserById(int id) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User  not found with ID: " + id));
        return UsersResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .phone(user.getPhone())
                .build();
    }

    @Override
    public UsersResponse getUserByPhone(String phone) {
        Users user = userRepository.findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("User  not found with phone: " + phone));
        return UsersResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .username(user.getUsername())
                .phone(user.getPhone())
                .build();
    }

    @Override
    public UsersResponse updateUser (int id, UsersRequest request) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User  not found with ID: " + id));

        // Update user fields
        user.setFullName(request.getFullName());
        user.setUsername(request.getUsername());
        user.setPhone(request.getPhone());
        user.setPassword(request.getPassword()); // Consider hashing the password

        Users updatedUser  = userRepository.save(user);

        // Create an audit trail for user update
        AuditTrailsRequest auditRequest = new AuditTrailsRequest();
        auditRequest.setAction("UPDATE");
        auditRequest.setDescription("User  updated with ID: " + updatedUser .getId());
        auditRequest.setRequest(request.toString());
        auditRequest.setResponse(updatedUser .toString());
        auditTrailsService.insertAuditTrails(auditRequest);

        return UsersResponse.builder()
                .id(updatedUser .getId())
                .fullName(updatedUser .getFullName())
                .username(updatedUser .getUsername())
                .phone(updatedUser .getPhone())
                .build();
    }

    @Override
    public Boolean checkToken(String token) {
        Users user = userRepository.findByToken(token);
        return user != null; // Return true kalo ada user, false kalo ga ada
    }

    public RegisterResponse register(RegisterRequest request) {
        Users user = Users.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(request.getPassword())
                .token(UUID.randomUUID().toString())
                .build();

        Users savedUser  = userRepository.save(user);


        return RegisterResponse.builder()
                .token(savedUser.getToken())
                .build();
    }
}