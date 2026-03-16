package vn.com.linkjob.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;
import vn.com.linkjob.dto.auth.LoginResponseDTO;
import vn.com.linkjob.exception.AppException;
import vn.com.linkjob.exception.ErrorCode;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SecurityUtil {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Value("${security.jwt.base64-secret}")
    private String jwtKey;

    @Value("${security.jwt.access-token-validity-in-seconds}")
    private long accessTokenExpiration;

    @Value("${security.jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenExpiration;

    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS512;

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user.
     */
    public static Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(extractPrincipal(securityContext.getAuthentication()));
    }

    private static String extractPrincipal(Authentication authentication) {
        if (authentication == null) {
            return null;
        } else if (authentication.getPrincipal() instanceof UserDetails springSecurityUser) {
            return springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof Jwt jwt) {
            return jwt.getSubject();
        } else if (authentication.getPrincipal() instanceof String s) {
            return s;
        }
        return null;
    }

    public String createAccessToken(String email, LoginResponseDTO.UserLogin userLogin) {
        Instant now = Instant.now();
        Instant validity = now.plus(this.accessTokenExpiration, ChronoUnit.SECONDS);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(email)
                .claim("user", userLogin)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();

        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    public String createRefreshToken(String email, LoginResponseDTO.UserLogin user) {
        Instant now = Instant.now();
        Instant validity = now.plus(this.accessTokenExpiration, ChronoUnit.SECONDS);

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(email)
                .claim("user", user)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();

        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

    public ResponseCookie createCookie(String refreshToken) {
        return ResponseCookie.from("refresh-token", refreshToken)
                .httpOnly(true)
                .path("/")
                .maxAge(refreshTokenExpiration)
                .build();
    }

    public Jwt verifyMyToken(String token) {
        try {
            return jwtDecoder.decode(token);
        } catch (Exception e) {
            throw new AppException(ErrorCode.REFRESH_TOKEN_INVALID);
        }
    }
}
