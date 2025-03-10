package com.users.app.repository;

import com.users.app.entity.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UsersRepositoryTest {

    @Autowired
    private UsersRepository usersRepository;

    private Users testUser;

    @BeforeEach
    void setUp() {
        testUser = Users.builder()
                .fullName("John Doe")
                .email("john@example.com")
                .password("password123")
                .phone("08123456789")
                .username("johndoe")
                .token("abcd1234")
                .build();
        usersRepository.save(testUser);
    }

    @Test
    void testFindByEmail() {
        Optional<Users> foundUser = usersRepository.findByEmail("john@example.com");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUsername()).isEqualTo("johndoe");
    }

    @Test
    void testFindByUsername() {
        Optional<Users> foundUser = usersRepository.findByUsername("johndoe");
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getEmail()).isEqualTo("john@example.com");
    }

    @Test
    void testExistsByEmail() {
        boolean exists = usersRepository.existsByEmail("john@example.com");
        assertThat(exists).isTrue();
    }

    @Test
    void testExistsByUsername() {
        boolean exists = usersRepository.existsByUsername("johndoe");
        assertThat(exists).isTrue();
    }

    @Test
    void testFindByEmail_NotFound() {
        Optional<Users> foundUser = usersRepository.findByEmail("notfound@example.com");
        assertThat(foundUser).isNotPresent();
    }
}
