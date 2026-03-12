package vn.com.linkjob.controller;

import com.turkraft.springfilter.boot.Filter;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.linkjob.domain.Company;
import vn.com.linkjob.dto.company.CompanyRequestDTO;
import vn.com.linkjob.dto.company.CompanyResponseDTO;
import vn.com.linkjob.dto.paginate.ResultPaginationDTO;
import vn.com.linkjob.service.CompanyService;
import vn.com.linkjob.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1/companies")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CompanyController {
    CompanyService companyService;

    @PostMapping
    @ApiMessage("create new company")
    public ResponseEntity<CompanyResponseDTO> createNewCompany(@RequestBody @Valid CompanyRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyService.createCompany(request));
    }

    @PutMapping("/{id}")
    @ApiMessage("edit company")
    public ResponseEntity<CompanyResponseDTO> updateCompany(@PathVariable long id,
                                                            @RequestBody CompanyRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.updateCompany(id, request));
    }

    @DeleteMapping("/{id}")
    @ApiMessage("delete company")
    public ResponseEntity<Void> deleteCompany(@PathVariable long id) {
        companyService.deleteCompany(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping
    @ApiMessage("get company with paginate, sort, filter")
    public ResponseEntity<ResultPaginationDTO> getCompaniesWithPagination(Pageable pageable,
                                                                          @Filter Specification<Company> spec) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(companyService.getCompaniesWithPaginate(pageable, spec));
    }
}
