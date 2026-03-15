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
    // system
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", "Tài nguyên không tồn tại trong hệ thống", HttpStatus.BAD_REQUEST),

    // auth
    UN_AUTHENTICATED("UN_AUTHENTICATED", "Bạn cần đăng nhập để thực hiện chức năng này", HttpStatus.UNAUTHORIZED),
    UN_AUTHORIZED("UN_AUTHORIZED", "Bạn không có quyền truy cập tài nguyên này", HttpStatus.FORBIDDEN),

    // user
    EMAIL_EXISTED("EMAIL_EXISTED", "Email đã tồn tại trong hệ thống", HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST("USER_NOT_EXIST", "Người dùng không tồn tại trong hệ thống", HttpStatus.BAD_REQUEST),
    GENDER_INVALID("GENDER_INVALID", "Giới tính không hợp lệ", HttpStatus.BAD_REQUEST),

    // company
    COMPANY_NOT_EXIST("COMPANY_NOT_EXIST", "Công ty không tồn tại trong hệ thống", HttpStatus.BAD_REQUEST),
    ;
    String code;
    String message;
    HttpStatusCode statusCode;
}
