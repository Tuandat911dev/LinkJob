package vn.com.linkjob.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vn.com.linkjob.domain.Role;
import vn.com.linkjob.dto.role.RoleCreateRequestDTO;
import vn.com.linkjob.dto.role.RoleResponseDTO;
import vn.com.linkjob.dto.role.RoleUpdateRequestDTO;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleCreateRequestDTO request);

    @Mapping(target = "permissions", source = "permissions")
    RoleResponseDTO toRoleResponseDTO(Role role);

    void updateRole(@MappingTarget Role role, RoleUpdateRequestDTO request);
}
