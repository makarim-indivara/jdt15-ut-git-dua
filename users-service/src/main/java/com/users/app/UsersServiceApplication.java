package com.customer.app.customer_service.service;

import com.customer.app.customer_service.client.dto.*;

public interface UsersService {
	UsersResponse save(UsersRequest request);
	UsersResponse getUserById(int id);
	UsersResponse getUserByPhone(String phone);
	UsersResponse updateUser (int id, UsersRequest request);
	RegisterResponse register(RegisterRequest request);

	Boolean checkToken(String token);

}

