package vn.com.linkjob.dto.permission;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionResponseDTO {
    long id;
    String name;
    String apiPath;
    String method;
    String module;
}
