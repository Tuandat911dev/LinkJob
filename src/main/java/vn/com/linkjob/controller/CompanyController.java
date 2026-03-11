package vn.com.linkjob.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.linkjob.dto.company.CompanyRequestDTO;
import vn.com.linkjob.dto.company.CompanyResponseDTO;
import vn.com.linkjob.service.CompanyService;

@RestController
@RequestMapping("/api/v1/companies")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CompanyController {
    CompanyService companyService;

    @PostMapping
    public ResponseEntity<CompanyResponseDTO> createNewCompany(@RequestBody @Valid CompanyRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyService.createCompany(request));
    }
}
