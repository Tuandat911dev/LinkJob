package vn.com.linkjob.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.linkjob.dto.role.RoleCreateRequestDTO;
import vn.com.linkjob.dto.role.RoleResponseDTO;
import vn.com.linkjob.dto.role.RoleUpdateRequestDTO;
import vn.com.linkjob.service.RoleService;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/v1/roles")
public class RoleController {
    RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleResponseDTO> createRole(@Valid @RequestBody RoleCreateRequestDTO request) {
        return ResponseEntity.ok(roleService.createRole(request));
    }

    @PutMapping
    public ResponseEntity<RoleResponseDTO> updateRole(@Valid @RequestBody RoleUpdateRequestDTO request) {
        return ResponseEntity.ok(roleService.updateRole(request));
    }
}