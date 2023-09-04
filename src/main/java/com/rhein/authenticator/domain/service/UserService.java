package com.rhein.authenticator.domain.service;

import com.rhein.authenticator.domain.model.User;
import com.rhein.authenticator.domain.request.UserRequestPostBody;
import jakarta.servlet.http.Cookie;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save (UserRequestPostBody user);
    User loadUserByUsername(String username);
    Cookie createCookie(String jwt);
}
