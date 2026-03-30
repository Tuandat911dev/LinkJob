package vn.com.linkjob.dto.resume;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateResumeResDTO {
    long id;
    Instant createdAt;
    String createdBy;
}
