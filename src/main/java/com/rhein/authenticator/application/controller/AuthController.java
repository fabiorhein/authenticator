package com.rhein.authenticator.application.controller;

import com.rhein.authenticator.application.util.JwtUtils;
import com.rhein.authenticator.domain.model.User;
import com.rhein.authenticator.domain.request.UserRequestAuthenticatePostBody;
import com.rhein.authenticator.domain.request.UserRequestPostBody;
import com.rhein.authenticator.domain.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRequestPostBody userRequestPostBody){
        return ResponseEntity.ok(userService.save(userRequestPostBody));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody UserRequestAuthenticatePostBody userRequestAuthPostBody, HttpServletResponse response) {
        try {
            String username = userRequestAuthPostBody.getUsername();

            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(
                            userRequestAuthPostBody.getUsername(), userRequestAuthPostBody.getPassword(),
                            new ArrayList<>()));
            final User user = userService.loadUserByUsername(username);
            if (user != null) {
                String jwt = jwtUtils.generateToken(user);
                response.addCookie(userService.createCookie(jwt));
                return ResponseEntity.ok(jwt);
            }
            return ResponseEntity.status(400).body("Error authenticating");
        } catch (Exception ex) {
            System.out.println(ex);
            return ResponseEntity.status(400).body(ex.getMessage());
        }
    }
}
