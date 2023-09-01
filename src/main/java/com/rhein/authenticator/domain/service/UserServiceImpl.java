package com.rhein.authenticator.domain.service;

import com.rhein.authenticator.domain.mapper.UserMapper;
import com.rhein.authenticator.domain.model.User;
import com.rhein.authenticator.domain.request.UserRequestPostBody;
import com.rhein.authenticator.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserRequestPostBody userRequestPostBody) {
        return userRepository.save(UserMapper.INSTANCE.toUser(userRequestPostBody));
    }
}
