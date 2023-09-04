package com.rhein.authenticator.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequestAuthenticatePostBody {
    private String username;
    private String password;
}
