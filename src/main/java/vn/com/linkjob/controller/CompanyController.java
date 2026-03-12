package vn.com.linkjob.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.linkjob.dto.company.CompanyRequestDTO;
import vn.com.linkjob.dto.company.CompanyResponseDTO;
import vn.com.linkjob.dto.paginate.ResultPaginationDTO;
import vn.com.linkjob.service.CompanyService;

import java.util.List;
import java.util.Optional;

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

//    @GetMapping
//    public ResponseEntity<List<CompanyResponseDTO>> getAllCompanies() {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(companyService.getAllCompanies());
//    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponseDTO> updateCompany(@PathVariable long id,
                                                            @RequestBody CompanyRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.updateCompany(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable long id) {
        companyService.deleteCompany(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping
    public ResponseEntity<ResultPaginationDTO> getCompaniesWithPagination(@RequestParam("current") Optional<String> currentOpt,
                                                                          @RequestParam("pageSize") Optional<String> pageSizeOpt) {
        int current = 1;
        int pageSize = 10;
        try {
            if (currentOpt.isPresent()) {
                current = Integer.parseInt(currentOpt.get());
            }

            if (pageSizeOpt.isPresent()) {
                pageSize = Integer.parseInt(pageSizeOpt.get());
            }
        } catch (Exception ignore) {
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.getCompaniesWithPaginate(current, pageSize));
    }
}
