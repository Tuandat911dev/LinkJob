package vn.com.linkjob.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.com.linkjob.domain.Permission;
import vn.com.linkjob.domain.Role;
import vn.com.linkjob.dto.role.RoleCreateRequestDTO;
import vn.com.linkjob.dto.role.RoleResponseDTO;
import vn.com.linkjob.dto.role.RoleUpdateRequestDTO;
import vn.com.linkjob.exception.AppException;
import vn.com.linkjob.exception.ErrorCode;
import vn.com.linkjob.mapper.RoleMapper;
import vn.com.linkjob.repository.PermissionRepository;
import vn.com.linkjob.repository.RoleRepository;

import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;
    PermissionRepository permissionRepository;

    public RoleResponseDTO createRole(RoleCreateRequestDTO request) {
        Role newRole = roleMapper.toRole(request);
        List<Long> permissionIds = request.getPermissions()
                .stream()
                .map(RoleCreateRequestDTO.RolePermissionDTO::getId)
                .toList();
        List<Permission> permissions = permissionRepository.findAllById(permissionIds);
        newRole.setPermissions(permissions);

        return roleMapper.toRoleResponseDTO(roleRepository.save(newRole));
    }

    public RoleResponseDTO updateRole(RoleUpdateRequestDTO request) {
        Role currentRole = roleRepository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXIST));

        roleMapper.updateRole(currentRole, request);
        List<Long> permissionIds = request.getPermissions()
                .stream()
                .map(RoleCreateRequestDTO.RolePermissionDTO::getId)
                .toList();
        List<Permission> permissions = permissionRepository.findAllById(permissionIds);
        currentRole.setPermissions(permissions);
        roleRepository.save(currentRole);

        return roleMapper.toRoleResponseDTO(currentRole);
    }

    public void deleteRole(Long id) {
        Role currentRole = roleRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXIST));
        roleRepository.delete(currentRole);
    }
}
