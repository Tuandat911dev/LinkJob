package vn.com.linkjob.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import vn.com.linkjob.domain.Job;
import vn.com.linkjob.domain.Resume;
import vn.com.linkjob.domain.User;
import vn.com.linkjob.dto.resume.CreateResumeDTO;
import vn.com.linkjob.dto.resume.CreateResumeResDTO;
import vn.com.linkjob.dto.resume.UpdateResumeDTO;
import vn.com.linkjob.dto.resume.UpdateResumeResDTO;
import vn.com.linkjob.exception.AppException;
import vn.com.linkjob.exception.ErrorCode;
import vn.com.linkjob.mapper.ResumeMapper;
import vn.com.linkjob.repository.JobRepository;
import vn.com.linkjob.repository.ResumeRepository;
import vn.com.linkjob.repository.UserRepository;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ResumeService {
    ResumeRepository resumeRepository;
    UserRepository userRepository;
    JobRepository jobRepository;
    ResumeMapper resumeMapper;

    public CreateResumeResDTO createResume(CreateResumeDTO request) {
        User user = userRepository.findById(request.getUser().getId()).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXIST)
        );

        Job job = jobRepository.findById(request.getJob().getId()).orElseThrow(
                () -> new AppException(ErrorCode.JOB_NOT_EXIST)
        );

        Resume newResume = resumeMapper.toResume(request);
        newResume.setUser(user);
        newResume.setJob(job);

        return resumeMapper.toCreateResumeResponseDTO(resumeRepository.save(newResume));
    }

    public UpdateResumeResDTO updateResume(UpdateResumeDTO request) {
        Resume currentResume = resumeRepository.findById(request.getId()).orElseThrow(
                () -> new AppException(ErrorCode.RESUME_NOT_EXIST)
        );

        currentResume.setStatus(request.getStatus());
        Resume updatedResume = resumeRepository.save(currentResume);

        return UpdateResumeResDTO.builder()
                .createdAt(updatedResume.getCreatedAt())
                .createdBy(updatedResume.getCreatedBy())
                .build();
    }
}
