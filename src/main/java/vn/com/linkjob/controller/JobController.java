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
import vn.com.linkjob.domain.Job;
import vn.com.linkjob.dto.job.CreateJobRequestDTO;
import vn.com.linkjob.dto.job.JobResponseDTO;
import vn.com.linkjob.dto.job.UpdateJobRequestDTO;
import vn.com.linkjob.dto.paginate.ResultPaginationDTO;
import vn.com.linkjob.service.JobService;


@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobs")
public class JobController {
    JobService jobService;

    @PostMapping
    public ResponseEntity<JobResponseDTO> createJob(@Valid @RequestBody CreateJobRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(jobService.createJob(request));
    }

    @PutMapping
    public ResponseEntity<JobResponseDTO> updateJob(@Valid @RequestBody UpdateJobRequestDTO requestDTO) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(jobService.updateJob(requestDTO));
    }

    @GetMapping
    public ResponseEntity<ResultPaginationDTO> getJobsWithPagination(Pageable pageable,
                                                                     @Filter Specification<Job> spec) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(jobService.getJobsWithPagination(pageable, spec));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDTO> getJobById(@PathVariable("id") long id) {
        return ResponseEntity
                .ok()
                .body(jobService.getJobById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable long id) {
        jobService.deleteJob(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
