package vn.com.linkjob.dto.file;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadFileResDTO {
    String fileName;
    Instant uploadedAt;
}
