package vn.com.linkjob.dto.resume;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.com.linkjob.util.enums.ResumeStatusEnum;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumeResDTO {
    long id;
    String email;
    ResumeStatusEnum status;
    Instant createdAt;
    Instant updatedAt;
    String createdBy;
    String updatedBy;
    UserResumeDTO user;
    JobResumeDTO job;

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UserResumeDTO {
        long id;
        String name;
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JobResumeDTO {
        long id;
        String name;
    }
}
