package vn.com.linkjob.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.com.linkjob.domain.Company;
import vn.com.linkjob.domain.Job;
import vn.com.linkjob.domain.Skill;
import vn.com.linkjob.dto.job.CreateJobRequestDTO;
import vn.com.linkjob.dto.job.JobResponseDTO;
import vn.com.linkjob.dto.job.UpdateJobRequestDTO;
import vn.com.linkjob.dto.paginate.ResultPaginationDTO;
import vn.com.linkjob.exception.AppException;
import vn.com.linkjob.exception.ErrorCode;
import vn.com.linkjob.mapper.JobMapper;
import vn.com.linkjob.repository.JobRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class JobService {
    JobRepository jobRepository;
    JobMapper jobMapper;
    CompanyService companyService;
    SkillService skillService;

    private void updateCommonData(Job currentJob, long companyId, List<Long> skillIds) {
        List<Skill> skills = new ArrayList<>();
        Company company = companyService.getCompanyById(companyId);
        if (company != null) {
            currentJob.setCompany(company);
        }

        for (long skillId : skillIds) {
            Optional<Skill> skill = skillService.getSkillById(skillId);
            skill.ifPresent(skills::add);
        }

        currentJob.setSkills(skills);
    }

    public JobResponseDTO createJob(CreateJobRequestDTO request) {
        Job newJob = jobMapper.toJob(request);
        updateCommonData(newJob, request.getCompanyId(), request.getSkills().getSkillId());

        return jobMapper.toJobResponseDTO(jobRepository.save(newJob));
    }

    public JobResponseDTO updateJob(UpdateJobRequestDTO request) {
        Job currentJob = jobRepository.findById(request.getId()).orElseThrow(
                () -> new AppException(ErrorCode.JOB_NOT_EXIST)
        );
        jobMapper.updateJob(currentJob, request);
        updateCommonData(currentJob, request.getCompanyId(), request.getSkills().getSkillId());

        return jobMapper.toJobResponseDTO(jobRepository.save(currentJob));
    }

    public ResultPaginationDTO getJobsWithPagination(Pageable pageable, Specification<Job> spec) {
        Page<Job> jobPage = jobRepository.findAll(spec, pageable);
        List<JobResponseDTO> jobs = jobPage.getContent().stream()
                .map(jobMapper::toJobResponseDTO)
                .toList();

        return ResultPaginationDTO.builder()
                .meta(ResultPaginationDTO.Meta.builder()
                        .pageSize(jobPage.getSize())
                        .page(jobPage.getNumber() + 1)
                        .total(jobPage.getTotalElements())
                        .pages(jobPage.getTotalPages())
                        .build())
                .result(jobs)
                .build();
    }

    public void deleteJob(long id) {
        Job currentJob = jobRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.JOB_NOT_EXIST)
        );

        jobRepository.delete(currentJob);
    }

    public JobResponseDTO getJobById(long id) {
        Job currentJob = jobRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.JOB_NOT_EXIST)
        );

        return jobMapper.toJobResponseDTO(currentJob);
    }
}
