package com.rhein.authenticator.infrastructure;

import com.rhein.authenticator.domain.model.User;
import com.rhein.authenticator.util.UserCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
@DataJpaTest
@DisplayName("User Repository Test")
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Save creates user when Successful")
    public void save_PersistUser_WhenSuccessful() {
        User userToBeSave = UserCreator.createUserToBeSave();
        User savedUser = userRepository.save(userToBeSave);
        Assertions.assertThat(savedUser.getId()).isNotNull();
        Assertions.assertThat(savedUser.getUsername()).isNotNull();
        Assertions.assertThat(savedUser.getUsername()).isEqualTo(userToBeSave.getUsername());
    }

    @Test
    @DisplayName("findByUsername creates user when Successful")
    public void findByUsername_ReturnUser_WhenSuccessful() {
        User userToBeSave = UserCreator.createUserToBeSave();
        User savedUser = userRepository.save(userToBeSave);
        String username = savedUser.getUsername();
        User userByUsername = userRepository.findByUsername(username).get();
        Assertions.assertThat(savedUser.getId()).isNotNull();
        Assertions.assertThat(savedUser.getUsername())
                .isNotNull()
                .isEqualTo(userByUsername.getUsername());
    }

}