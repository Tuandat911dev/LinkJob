package vn.com.linkjob.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import vn.com.linkjob.domain.Resume;
import vn.com.linkjob.dto.resume.CreateResumeDTO;
import vn.com.linkjob.dto.resume.CreateResumeResDTO;

@Mapper(componentModel = "spring")
public interface ResumeMapper {
    Resume toResume(CreateResumeDTO request);

    CreateResumeResDTO toCreateResumeResponseDTO(Resume resume);

//    void updateResume(@MappingTarget Resume resume, ResumeRequestDTO request);
}
