package vn.com.linkjob.util;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import vn.com.linkjob.dto.api.RestResponse;
import vn.com.linkjob.util.annotation.ApiMessage;

import java.util.Optional;

@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class FormatRestResponse implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            Object body,
            MethodParameter returnType,
            MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType,
            ServerHttpRequest request,
            ServerHttpResponse response) {
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();

        int status = servletResponse.getStatus();
        RestResponse<Object> restResponse = null;
        if (status >= 400) {
            if (body instanceof RestResponse<?>) {
                restResponse = RestResponse.builder()
                        .status("failed")
                        .error(((RestResponse<?>) body).getError())
                        .statusCode(status)
                        .message(Optional.ofNullable(((RestResponse<?>) body).getMessage()).orElse("CALL_API_FAILED"))
                        .build();
            } else {
                return body;
            }
        } else {
            ApiMessage message = returnType.getMethodAnnotation(ApiMessage.class);
            restResponse = RestResponse.builder()
                    .status("success")
                    .statusCode(status)
                    .data(body)
                    .message(message != null ? message.value() : "CALL_API_SUCCESS")
                    .build();
        }

        return restResponse;
    }
}
