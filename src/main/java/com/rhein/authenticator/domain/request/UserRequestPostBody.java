package com.rhein.authenticator.domain.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestPostBody {
    private String fullName;
    private String username;
    private String password;
    private String authorities;
}
