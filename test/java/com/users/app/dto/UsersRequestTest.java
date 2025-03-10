package com.users.app.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class UsersRequestTest {

    @Test
    void testUsersRequestSettersAndGetters() {
        UsersRequest user = new UsersRequest();
        user.setFullName("John Doe");
        user.setEmail("john@example.com");
        user.setPassword("password123");
        user.setPhone("08123456789");

        assertThat(user.getFullName()).isEqualTo("John Doe");
        assertThat(user.getEmail()).isEqualTo("john@example.com");
        assertThat(user.getPassword()).isEqualTo("password123");
        assertThat(user.getPhone()).isEqualTo("08123456789");
    }

    @Test
    void testUsersRequestConstructor() {
        UsersRequest user1 = new UsersRequest();
        UsersRequest user2 = new UsersRequest();

        assertThat(user1).isNotNull();
        assertThat(user2).isNotNull();
    }
}
