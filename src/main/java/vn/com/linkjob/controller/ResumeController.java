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
import vn.com.linkjob.dto.resume.CreateResumeDTO;
import vn.com.linkjob.dto.resume.CreateResumeResDTO;
import vn.com.linkjob.service.ResumeService;
import vn.com.linkjob.util.annotation.ApiMessage;

@RestController
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequestMapping("/api/v1/resumes")
@RequiredArgsConstructor
public class ResumeController {
    ResumeService resumeService;

    @PostMapping
    @ApiMessage("Create new Resume")
    public ResponseEntity<CreateResumeResDTO> createResume(@RequestBody @Valid CreateResumeDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(resumeService.createResume(request));
    }
}
