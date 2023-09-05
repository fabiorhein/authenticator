package com.rhein.authenticator.domain.service;

import com.rhein.authenticator.domain.mapper.UserMapper;
import com.rhein.authenticator.domain.model.User;
import com.rhein.authenticator.domain.request.UserRequestPostBody;
import com.rhein.authenticator.infrastructure.UserRepository;
import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User save(UserRequestPostBody userRequestPostBody) {
        User user = UserMapper.INSTANCE.toUser(userRequestPostBody);
        user.setPassword(new BCryptPasswordEncoder().encode(userRequestPostBody.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Cookie createCookie(String jwt) {
        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
//                cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setPath("/"); // Global
        return cookie;
    }
}
