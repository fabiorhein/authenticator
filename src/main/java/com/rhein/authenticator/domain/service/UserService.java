package com.rhein.authenticator.domain.service;

import com.rhein.authenticator.domain.request.UserRequestPostBody;
import com.rhein.authenticator.domain.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save (UserRequestPostBody user);
    UserDetails loadUserByUsername(String username);
}
