package vn.com.linkjob.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.com.linkjob.dto.permission.PermissionCreateRequestDTO;
import vn.com.linkjob.dto.permission.PermissionResponseDTO;
import vn.com.linkjob.mapper.PermissionMapper;
import vn.com.linkjob.repository.PermissionRepository;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponseDTO createPermission(PermissionCreateRequestDTO request) {
        return permissionMapper
                .toPermissionResponseDTO(
                        permissionRepository.save(
                                permissionMapper.toPermission(request)
                        )
                );
    }
}
