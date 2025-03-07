package com.users.app.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsersResponse {
    private String fullName;
    private String email;
    private String phone;
}
