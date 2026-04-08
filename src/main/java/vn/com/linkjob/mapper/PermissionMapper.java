package vn.com.linkjob.mapper;

import org.mapstruct.Mapper;
import vn.com.linkjob.domain.Permission;
import vn.com.linkjob.dto.permission.PermissionCreateRequestDTO;
import vn.com.linkjob.dto.permission.PermissionResponseDTO;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionCreateRequestDTO request);

    PermissionResponseDTO toPermissionResponseDTO(Permission permission);
}
