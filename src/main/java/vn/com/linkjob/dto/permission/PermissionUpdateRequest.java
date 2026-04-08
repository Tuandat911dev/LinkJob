package vn.com.linkjob.dto.permission;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionUpdateRequest {
    @NotNull(message = "ID không được để trống")
    Long id;
    @NotBlank(message = "Tên không được để trống")
    String name;
    @NotBlank(message = "Đường dẫn API không được để trống")
    String apiPath;
    @NotBlank(message = "Phương thức không được để trống")
    String method;
    @NotBlank(message = "Module không được để trống")
    String module;
}
