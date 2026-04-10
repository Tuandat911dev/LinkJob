package vn.com.linkjob.dto.role;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleCreateRequestDTO {
    @NotBlank(message = "Tên vai trò không được để trống")
    String name;
    @NotBlank(message = "Mô tả vai trò không được để trống")
    String description;
    boolean active;
    List<RolePermissionDTO> permissions;

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RolePermissionDTO {
        long id;
    }
}
