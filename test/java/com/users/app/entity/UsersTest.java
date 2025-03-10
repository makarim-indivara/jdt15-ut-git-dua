package com.users.app.entity;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class UsersTest {

    @Test
    void testUsersSettersAndGetters() {
        Users user = Users.builder()
                .custId(1L)
                .fullName("John Doe")
                .email("john@example.com")
                .password("password123")
                .phone("08123456789")
                .username("johndoe")
                .token("abcd1234")
                .build();

        assertThat(user.getCustId()).isEqualTo(1L);
        assertThat(user.getFullName()).isEqualTo("John Doe");
        assertThat(user.getEmail()).isEqualTo("john@example.com");
        assertThat(user.getPassword()).isEqualTo("password123");
        assertThat(user.getPhone()).isEqualTo("08123456789");
        assertThat(user.getUsername()).isEqualTo("johndoe");
        assertThat(user.getToken()).isEqualTo("abcd1234");
    }

    @Test
    void testUsersEqualsAndHashCode() {
        Users user1 = Users.builder()
                .custId(1L)
                .email("john@example.com")
                .build();

        Users user2 = Users.builder()
                .custId(1L)
                .email("john@example.com")
                .build();

        assertThat(user1).isEqualTo(user2);
        assertThat(user1.hashCode()).isEqualTo(user2.hashCode());
    }
}
