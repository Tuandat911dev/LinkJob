package vn.com.linkjob.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.com.linkjob.domain.Resume;
import vn.com.linkjob.dto.resume.CreateResumeDTO;
import vn.com.linkjob.dto.resume.CreateResumeResDTO;
import vn.com.linkjob.dto.resume.ResumeResDTO;

@Mapper(componentModel = "spring")
public interface ResumeMapper {
    Resume toResume(CreateResumeDTO request);

    CreateResumeResDTO toCreateResumeResponseDTO(Resume resume);

    @Mapping(source = "resume.user", target = "user")
    @Mapping(source = "resume.job", target = "job")
    ResumeResDTO toResumeResDTO(Resume resume);
}
