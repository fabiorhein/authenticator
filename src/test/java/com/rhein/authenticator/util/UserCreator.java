package com.rhein.authenticator.util;

import com.rhein.authenticator.domain.model.User;

import java.util.UUID;

public class UserCreator {
    private static final User USER = User.builder()
            .username("john")
            .fullName("John House")
            .password("12345@")
            .authorities("READ")
            .build();

    public static User createUserToBeSave() {
        return USER;
    }

    public static User createValidUser() {
        USER.setId(UUID.fromString("ad19f068-c91c-4603-8a09-84f1f72cb50f"));
        return USER;
    }

    public static User createValidUpdatedUser() {
        USER.setId(UUID.fromString("ad19f068-c91c-4603-8a09-84f1f72cb50f"));
        USER.setAuthorities("READ,WRITE");
        USER.setFullName("John White House");
        return USER;
    }
}
