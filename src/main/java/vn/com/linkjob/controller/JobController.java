package vn.com.linkjob.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.com.linkjob.dto.job.CreateJobRequestDTO;
import vn.com.linkjob.dto.job.JobResponseDTO;
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
}
