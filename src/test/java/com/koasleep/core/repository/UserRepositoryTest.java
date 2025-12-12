package com.koasleep.core.repository;

import com.koasleep.core.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase
@TestPropertySource(properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Rollback
    public void shouldSaveAndFindUser() {
        User newUser = new User();
        newUser.setEmail("test-engineer@koasleep.com");
        newUser.setFullName("Test Engineer");

        User savedUser = userRepository.save(newUser);

        assertThat(savedUser.getId()).isNotNull();
        System.out.println("Generated ID: " + savedUser.getId());

        Optional<User> foundUser = userRepository.findByEmail("test-engineer@koasleep.com");

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getFullName()).isEqualTo("Test Engineer");
        assertThat(foundUser.get().getCreatedAt()).isNotNull(); // Verify @PrePersist worked
    }
}