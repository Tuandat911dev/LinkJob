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
    REFRESH_TOKEN_INVALID("REFRESH_TOKEN_INVALID", "Refresh token không hợp lệ", HttpStatus.BAD_REQUEST),

    // user
    EMAIL_EXISTED("EMAIL_EXISTED", "Email đã tồn tại trong hệ thống", HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST("USER_NOT_EXIST", "Người dùng không tồn tại trong hệ thống", HttpStatus.BAD_REQUEST),
    GENDER_INVALID("GENDER_INVALID", "Giới tính không hợp lệ", HttpStatus.BAD_REQUEST),

    // company
    COMPANY_NOT_EXIST("COMPANY_NOT_EXIST", "Công ty không tồn tại trong hệ thống", HttpStatus.BAD_REQUEST),

    // job
    JOB_LEVEL_INVALID("JOB_LEVEL_INVALID", "Level công việc không hợp lệ", HttpStatus.BAD_REQUEST),
    JOB_NOT_EXIST("JOB_NOT_EXIST", "Công việc này chưa tồn tại trong hệ thống", HttpStatus.BAD_REQUEST),

    // skill
    SKILL_EXISTED("SKILL_EXISTED", "Kỹ năng đã tồn tại trong hệ thống, vui lòng chọn kỹ năng có sẵn",
            HttpStatus.BAD_REQUEST),
    SKILL_NOT_EXIST("SKILL_NOT_EXIST", "Kỹ năng không tồn tại trong hệ thống", HttpStatus.BAD_REQUEST),
    SKILL_IN_USE("SKILL_IN_USE", "Kỹ năng đang được sử dụng, không thể xóa", HttpStatus.BAD_REQUEST),

    // file
    FILE_EXTENSION_INVALID("FILE_EXTENSION_INVALID", "Định dạng file không hợp lệ, chấp nhận pdf, jpg, jpeg, png, " +
            "doc, docx", HttpStatus.BAD_REQUEST),
    FILE_NOT_EMPTY("FILE_NOT_EMPTY", "File không được để trống", HttpStatus.BAD_REQUEST),
    FILE_TOO_LARGE("FILE_TOO_LARGE", "Dung lượng file quá lớn, chấp nhận dưới 50MB", HttpStatus.BAD_REQUEST),

    // resume
    RESUME_STATUS_INVALID("RESUME_STATUS_INVALID", "Trạng thái CV không hợp lệ", HttpStatus.BAD_REQUEST),
    RESUME_NOT_EXIST("RESUME_NOT_EXIST", "CV không tồn tại trong hệ thống", HttpStatus.BAD_REQUEST),

    // permission
    PERMISSION_NOT_EXIST("PERMISSION_NOT_EXIST", "Quyền không tồn tại trong hệ thống", HttpStatus.BAD_REQUEST),

    // role
    ROLE_NOT_EXIST("ROLE_NOT_EXIST", "Vai trò không tồn tại trong hệ thống", HttpStatus.BAD_REQUEST)
    ;
    String code;
    String message;
    HttpStatusCode statusCode;
}
