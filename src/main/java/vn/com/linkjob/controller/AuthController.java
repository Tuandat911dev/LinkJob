package vn.com.linkjob.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.com.linkjob.domain.User;
import vn.com.linkjob.dto.auth.LoginDTO;
import vn.com.linkjob.dto.auth.LoginResponseDTO;
import vn.com.linkjob.exception.AppException;
import vn.com.linkjob.exception.ErrorCode;
import vn.com.linkjob.service.UserService;
import vn.com.linkjob.util.SecurityUtil;
import vn.com.linkjob.util.annotation.ApiMessage;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/api/v1/auth")
public class AuthController {
    AuthenticationManagerBuilder authenticationManagerBuilder;
    SecurityUtil securityUtil;
    UserService userService;

    @PostMapping("/login")
    @ApiMessage("Login account")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO request) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        User currentUser = userService.getUserForLogin(request.getUsername());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LoginResponseDTO.UserLogin userLogin = LoginResponseDTO.UserLogin.builder()
                .email(currentUser.getEmail())
                .name(currentUser.getName())
                .id(currentUser.getId())
                .build();

        String jwt = securityUtil.createAccessToken(authentication, userLogin);

        String refreshToken = securityUtil.createRefreshToken(
                currentUser.getEmail(),
                userLogin
        );

        userService.updateRefreshToken(currentUser.getEmail(), refreshToken);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.SET_COOKIE, securityUtil.createCookie(refreshToken).toString())
                .body(LoginResponseDTO.builder()
                        .accessToken(jwt)
                        .user(userLogin)
                        .build());
    }

    @GetMapping("/account")
    @ApiMessage("Fetch user info")
    public ResponseEntity<LoginResponseDTO.UserLogin> fetchUserLogin() {
        String currentEmail = SecurityUtil.getCurrentUserLogin().orElseThrow(
                () -> new AppException(ErrorCode.UN_AUTHENTICATED)
        );

        User currentUser = userService.getUserForLogin(currentEmail);
        LoginResponseDTO.UserLogin userLogin = LoginResponseDTO.UserLogin.builder()
                .email(currentUser.getEmail())
                .name(currentUser.getName())
                .id(currentUser.getId())
                .build();

        return ResponseEntity.ok()
                .body(userLogin);
    }

}
