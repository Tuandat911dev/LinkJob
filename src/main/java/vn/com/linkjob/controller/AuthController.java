package vn.com.linkjob.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.linkjob.domain.User;
import vn.com.linkjob.dto.auth.LoginDTO;
import vn.com.linkjob.dto.auth.LoginResponseDTO;
import vn.com.linkjob.service.UserService;
import vn.com.linkjob.util.SecurityUtil;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/api/v1")
public class AuthController {
    AuthenticationManagerBuilder authenticationManagerBuilder;
    SecurityUtil securityUtil;
    UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO request) {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = securityUtil.createToken(authentication);
        User currentUser = userService.getUserForLogin(request.getUsername());

        return ResponseEntity.ok().body(
                LoginResponseDTO.builder()
                        .accessToken(jwt)
                        .user(LoginResponseDTO.UserLogin.builder()
                                .email(currentUser.getEmail())
                                .name(currentUser.getName())
                                .id(currentUser.getId())
                                .build())
                        .build()
        );
    }
}
