package vn.com.linkjob.controller;

import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.linkjob.domain.Permission;
import vn.com.linkjob.domain.Resume;
import vn.com.linkjob.dto.paginate.ResultPaginationDTO;
import vn.com.linkjob.dto.permission.PermissionCreateRequestDTO;
import vn.com.linkjob.dto.permission.PermissionResponseDTO;
import vn.com.linkjob.dto.permission.PermissionUpdateRequest;
import vn.com.linkjob.service.PermissionService;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/v1/permissions")
public class PermissionController {
    PermissionService permissionService;

    @PostMapping
    public ResponseEntity<PermissionResponseDTO> createPermission(
            @Valid @RequestBody PermissionCreateRequestDTO request
    ) {
        return ResponseEntity
                .ok()
                .body(permissionService.createPermission(request));
    }

    @PutMapping
    public ResponseEntity<PermissionResponseDTO> updatePermission(
            @Valid @RequestBody PermissionUpdateRequest request
    ) {
        return ResponseEntity
                .ok()
                .body(permissionService.updatePermission(request));
    }

    @GetMapping
    public ResponseEntity<ResultPaginationDTO> getPermissionsWithPaginate(
            Pageable pageable,
            @Filter Specification<Permission> spec
    ) {
        return ResponseEntity
                .ok()
                .body(permissionService.getPermissionsWithPaginate(pageable, spec));
    }
}
