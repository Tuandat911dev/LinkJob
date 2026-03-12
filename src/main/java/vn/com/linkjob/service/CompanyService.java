package vn.com.linkjob.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.com.linkjob.domain.Company;
import vn.com.linkjob.dto.company.CompanyRequestDTO;
import vn.com.linkjob.dto.company.CompanyResponseDTO;
import vn.com.linkjob.exception.AppException;
import vn.com.linkjob.exception.ErrorCode;
import vn.com.linkjob.mapper.CompanyMapper;
import vn.com.linkjob.repository.CompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CompanyService {
    CompanyRepository companyRepository;
    CompanyMapper companyMapper;

    public CompanyResponseDTO createCompany(CompanyRequestDTO request) {
        Company newCompany = companyRepository.save(companyMapper.toCompany(request));

        return companyMapper.toCompanyResponseDTO(newCompany);
    }

    public List<CompanyResponseDTO> getAllCompanies() {
        return companyRepository.findAll().stream()
                .map(companyMapper::toCompanyResponseDTO)
                .toList();
    }

    public CompanyResponseDTO updateCompany(long id, CompanyRequestDTO request) {
        Company company = companyRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.COMPANY_NOT_EXIST)
        );
        companyMapper.updateCompany(company, request);

        return companyMapper.toCompanyResponseDTO(companyRepository.save(company));
    }
}
