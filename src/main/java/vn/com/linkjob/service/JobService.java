package vn.com.linkjob.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.com.linkjob.domain.Company;
import vn.com.linkjob.domain.Job;
import vn.com.linkjob.domain.Skill;
import vn.com.linkjob.dto.job.CreateJobRequestDTO;
import vn.com.linkjob.dto.job.JobResponseDTO;
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

    public JobResponseDTO createJob(CreateJobRequestDTO request) {
        Company company = companyService.getCompanyById(request.getCompanyId());
        List<Skill> skills = new ArrayList<>();

        Job newJob = jobMapper.toJob(request);

        if (company != null) {
            newJob.setCompany(company);
        }

        for (long skillId : request.getSkills().getSkillId()) {
            Optional<Skill> skill = skillService.getSkillById(skillId);
            skill.ifPresent(skills::add);
        }

        newJob.setSkills(skills);

        return jobMapper.toJobResponseDTO(jobRepository.save(newJob));
    }
}
