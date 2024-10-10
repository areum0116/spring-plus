package org.example.expert.domain.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.expert.domain.common.annotation.Auth;
import org.example.expert.domain.common.dto.AuthUser;
import org.example.expert.domain.user.dto.request.UserChangePasswordRequest;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.repository.UserRepository;
import org.example.expert.domain.user.service.UserService;
import org.example.expert.security.UserDetailsImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable long userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PutMapping("/users")
    public void changePassword(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody UserChangePasswordRequest userChangePasswordRequest) {
        userService.changePassword(userDetails.getId(), userChangePasswordRequest);
    }

    @GetMapping("/users/{nickname}")
    public ResponseEntity<UserResponse> getUserByNickname(@PathVariable String nickname) {
        return ResponseEntity.ok(userService.getUserByNickname(nickname));
    }
}
