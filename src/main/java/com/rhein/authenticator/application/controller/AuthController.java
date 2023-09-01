package com.rhein.authenticator.application.controller;

import com.rhein.authenticator.domain.request.UserRequestPostBody;
import com.rhein.authenticator.domain.model.User;
import com.rhein.authenticator.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRequestPostBody userRequestPostBody){
        return ResponseEntity.ok(userService.save(userRequestPostBody));
    }
}
