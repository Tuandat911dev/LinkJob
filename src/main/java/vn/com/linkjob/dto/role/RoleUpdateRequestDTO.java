package vn.com.linkjob.dto.role;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleUpdateRequestDTO {
    @NotNull(message = "ID vai trò không được để trống")
    Long id;
    @NotBlank(message = "Tên vai trò không được để trống")
    String name;
    @NotBlank(message = "Mô tả vai trò không được để trống")
    String description;
    boolean active;

    List<RoleCreateRequestDTO.RolePermissionDTO> permissions;

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
