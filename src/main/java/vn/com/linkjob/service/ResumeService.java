package vn.com.linkjob.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.com.linkjob.domain.Job;
import vn.com.linkjob.domain.Resume;
import vn.com.linkjob.domain.User;
import vn.com.linkjob.dto.paginate.ResultPaginationDTO;
import vn.com.linkjob.dto.resume.*;
import vn.com.linkjob.exception.AppException;
import vn.com.linkjob.exception.ErrorCode;
import vn.com.linkjob.mapper.ResumeMapper;
import vn.com.linkjob.repository.JobRepository;
import vn.com.linkjob.repository.ResumeRepository;
import vn.com.linkjob.repository.UserRepository;

import java.util.List;

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

    public void deleteResume(long id) {
        Resume currentResume = resumeRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.RESUME_NOT_EXIST)
        );

        resumeRepository.delete(currentResume);
    }

    public ResumeResDTO getResumeById(long id) {
        Resume currentResume = resumeRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.RESUME_NOT_EXIST)
        );

        return resumeMapper.toResumeResDTO(currentResume);
    }

    public ResultPaginationDTO getResumesWithPaginate(Pageable pageable, Specification<Resume> spec) {
        Page<Resume> resumes = resumeRepository.findAll(spec, pageable);
        List<ResumeResDTO> result = resumes.getContent().stream()
                .map(resumeMapper::toResumeResDTO)
                .toList();

        return ResultPaginationDTO.builder()
                .meta(ResultPaginationDTO.Meta.builder()
                        .pageSize(resumes.getSize())
                        .page(resumes.getNumber() + 1)
                        .total(resumes.getTotalElements())
                        .pages(resumes.getTotalPages())
                        .build())
                .result(result)
                .build();
    }
}
