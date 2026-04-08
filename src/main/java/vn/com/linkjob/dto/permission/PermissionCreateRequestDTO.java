package vn.com.linkjob.dto.permission;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionCreateRequestDTO {
    @NotBlank(message = "Tên không được để trống")
    String name;
    @NotBlank(message = "Đường dẫn API không được để trống")
    String apiPath;
    @NotBlank(message = "Phương thức không được để trống")
    String method;
    @NotBlank(message = "Module không được để trống")
    String module;
}
