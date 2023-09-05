package com.rhein.authenticator.domain.service;

import com.rhein.authenticator.domain.model.User;
import com.rhein.authenticator.infrastructure.UserRepository;
import com.rhein.authenticator.util.UserCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepositoryMock;

    @BeforeEach
    void setUp() {
        BDDMockito.when(userRepositoryMock.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(Optional.ofNullable(UserCreator.createValidUser()));

        BDDMockito.when(userRepositoryMock.save(ArgumentMatchers.any(User.class)))
                .thenReturn(UserCreator.createValidUser());
    }

    @Test
    void save() {
    }

    @Test
    @DisplayName("loadUserByUsername returns user when successful")
    void loadUserByUsername_returnsUser_WhenSuccessful() {
        String expectedUsername = UserCreator.createValidUser().getUsername();
        User user = userService.loadUserByUsername(expectedUsername);
        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getUsername()).isEqualTo(expectedUsername);
    }

    @Test
    @DisplayName("loadUserByUsername throws BadRequestException when user is not found")
    void loadUserByUsername_ThrowsUsernameNotFoundException_WhenUserIsNotFound(){
        BDDMockito.when(userRepositoryMock.findByUsername(ArgumentMatchers.anyString()))
                .thenReturn(Optional.empty());
        Assertions.assertThatExceptionOfType(UsernameNotFoundException.class)
                .isThrownBy(() -> userService.loadUserByUsername(""));
    }

    @Test
    void createCookie() {
    }
}