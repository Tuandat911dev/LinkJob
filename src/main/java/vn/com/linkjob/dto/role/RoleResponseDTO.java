package vn.com.linkjob.dto.role;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleResponseDTO {
    long id;
    String name;
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
        String name;
        String apiPath;
        String method;
        String module;
    }
}
