package vn.com.linkjob.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.com.linkjob.domain.Permission;
import vn.com.linkjob.domain.Role;
import vn.com.linkjob.dto.role.RoleCreateRequestDTO;
import vn.com.linkjob.dto.role.RoleResponseDTO;
import vn.com.linkjob.mapper.RoleMapper;
import vn.com.linkjob.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionService permissionService;

    public RoleResponseDTO createRole(RoleCreateRequestDTO request) {
        Role newRole = roleMapper.toRole(request);
        List<Permission> permissions = new ArrayList<>();
        request.getPermissions().forEach(
                permission -> {
                    Optional<Permission> p = permissionService.getPermissionById(permission.getId());
                    p.ifPresent(permissions::add);
                });

        newRole.setPermissions(permissions);

        return roleMapper.toRoleResponseDTO(roleRepository.save(newRole));
    }
}
