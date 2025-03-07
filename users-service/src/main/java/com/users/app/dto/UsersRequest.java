package com.users.app.dto;

import lombok.Data;

@Data
public class UsersRequest {
    private String fullName;
    private String email;
    private String password;
    private String phone;
}
