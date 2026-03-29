package vn.com.linkjob.dto.file;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.core.io.InputStreamResource;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DownloadFileResDTO {
    long fileLength;
    InputStreamResource resource;
}
