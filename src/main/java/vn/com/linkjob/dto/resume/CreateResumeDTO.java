package vn.com.linkjob.dto.resume;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.com.linkjob.util.enums.ResumeStatusEnum;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateResumeDTO {
    @NotBlank(message = "Email không được để trống")
    @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Email không đúng định dạng")
    String email;
    String url;
    ResumeStatusEnum status;
    UserCreateResumeDTO user;
    JobCreateResumeDTO job;

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserCreateResumeDTO {
        long id;
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JobCreateResumeDTO {
        long id;
    }
}
