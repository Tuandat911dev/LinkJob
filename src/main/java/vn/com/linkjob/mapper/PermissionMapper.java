package vn.com.linkjob.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import vn.com.linkjob.domain.Permission;
import vn.com.linkjob.dto.permission.PermissionCreateRequestDTO;
import vn.com.linkjob.dto.permission.PermissionResponseDTO;
import vn.com.linkjob.dto.permission.PermissionUpdateRequest;

@Mapper(componentModel = "spring")
public interface PermissionMapper {
    Permission toPermission(PermissionCreateRequestDTO request);

    PermissionResponseDTO toPermissionResponseDTO(Permission permission);

    void updatePermission(@MappingTarget Permission permission, PermissionUpdateRequest request);
}
