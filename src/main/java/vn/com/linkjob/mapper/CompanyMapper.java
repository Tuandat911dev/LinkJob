package vn.com.linkjob.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import vn.com.linkjob.domain.Company;
import vn.com.linkjob.dto.company.CompanyRequestDTO;
import vn.com.linkjob.dto.company.CompanyResponseDTO;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    Company toCompany(CompanyRequestDTO request);

    CompanyResponseDTO toCompanyResponseDTO(Company company);

    void updateCompany(@MappingTarget Company company, CompanyRequestDTO request);
}
