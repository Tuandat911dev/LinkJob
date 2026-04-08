package vn.com.linkjob.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.linkjob.dto.permission.PermissionCreateRequestDTO;
import vn.com.linkjob.dto.permission.PermissionResponseDTO;
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
}
