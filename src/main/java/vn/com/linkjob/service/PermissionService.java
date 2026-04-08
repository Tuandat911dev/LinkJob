package vn.com.linkjob.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.com.linkjob.domain.Permission;
import vn.com.linkjob.dto.paginate.ResultPaginationDTO;
import vn.com.linkjob.dto.permission.PermissionCreateRequestDTO;
import vn.com.linkjob.dto.permission.PermissionResponseDTO;
import vn.com.linkjob.dto.permission.PermissionUpdateRequest;
import vn.com.linkjob.exception.AppException;
import vn.com.linkjob.exception.ErrorCode;
import vn.com.linkjob.mapper.PermissionMapper;
import vn.com.linkjob.repository.PermissionRepository;

import java.util.List;

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

    public PermissionResponseDTO updatePermission(PermissionUpdateRequest request) {
        Permission permission = permissionRepository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.PERMISSION_NOT_EXIST));
        permissionMapper.updatePermission(permission, request);

        return permissionMapper.toPermissionResponseDTO(permissionRepository.save(permission));
    }

    public ResultPaginationDTO getPermissionsWithPaginate(Pageable pageable, Specification<Permission> spec) {
        Page<Permission> permissions = permissionRepository.findAll(spec, pageable);
        List<PermissionResponseDTO> result = permissions.getContent().stream()
                .map(permissionMapper::toPermissionResponseDTO)
                .toList();

        return ResultPaginationDTO.builder()
                .meta(ResultPaginationDTO.Meta.builder()
                        .pageSize(permissions.getSize())
                        .page(permissions.getNumber() + 1)
                        .total(permissions.getTotalElements())
                        .pages(permissions.getTotalPages())
                        .build())
                .result(result)
                .build();
    }
}
