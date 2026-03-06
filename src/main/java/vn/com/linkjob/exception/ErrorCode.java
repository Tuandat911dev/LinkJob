package vn.com.linkjob.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    // user
    EMAIL_EXISTED("EMAIL_EXISTED", "Email đã tồn tại trong hệ thống", HttpStatus.BAD_REQUEST)
    ;
    String code;
    String message;
    HttpStatusCode statusCode;
}
