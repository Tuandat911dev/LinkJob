package vn.com.linkjob.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import vn.com.linkjob.domain.Job;
import vn.com.linkjob.dto.job.CreateJobRequestDTO;
import vn.com.linkjob.dto.job.JobResponseDTO;
import vn.com.linkjob.dto.job.UpdateJobRequestDTO;

@Mapper(componentModel = "spring")
public interface JobMapper {
    @Mapping(target = "skills", ignore = true)
    Job toJob(CreateJobRequestDTO request);

    @Mapping(source = "company", target = "company")
    @Mapping(source = "skills", target = "skills")
    JobResponseDTO toJobResponseDTO(Job user);

    @Mapping(target = "skills", ignore = true)
    void updateJob(@MappingTarget Job user, UpdateJobRequestDTO request);
}
