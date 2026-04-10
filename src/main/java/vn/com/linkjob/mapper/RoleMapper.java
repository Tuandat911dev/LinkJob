package vn.com.linkjob.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.com.linkjob.domain.Role;
import vn.com.linkjob.dto.role.RoleCreateRequestDTO;
import vn.com.linkjob.dto.role.RoleResponseDTO;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleCreateRequestDTO request);

    @Mapping(target = "permissions", source = "permissions")
    RoleResponseDTO toRoleResponseDTO(Role role);
}
