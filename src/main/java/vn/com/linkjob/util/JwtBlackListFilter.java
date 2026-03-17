package vn.com.linkjob.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;
import org.springframework.security.oauth2.server.resource.web.DefaultBearerTokenResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import vn.com.linkjob.dto.api.RestResponse;
import vn.com.linkjob.exception.ErrorCode;

import java.io.IOException;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class JwtBlackListFilter extends OncePerRequestFilter {

    RedisTemplate<String, String> redisTemplate;

    private final ObjectMapper mapper;

    private final BearerTokenResolver bearerTokenResolver = new DefaultBearerTokenResolver();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = bearerTokenResolver.resolve(request);

        if (token != null) {
            String redisKey = "blacklist:" + token;
            if (redisTemplate.hasKey(redisKey)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");

                RestResponse<Object> res = RestResponse.builder()
                        .statusCode(HttpStatus.UNAUTHORIZED.value())
                        .error(ErrorCode.UN_AUTHENTICATED.getCode())
                        .message(ErrorCode.UN_AUTHENTICATED.getMessage())
                        .build();

                mapper.writeValue(response.getWriter(), res);
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}
