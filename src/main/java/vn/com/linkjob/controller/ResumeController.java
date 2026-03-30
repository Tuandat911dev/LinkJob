package vn.com.linkjob.controller;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.com.linkjob.dto.resume.CreateResumeDTO;
import vn.com.linkjob.dto.resume.CreateResumeResDTO;
import vn.com.linkjob.dto.resume.UpdateResumeDTO;
import vn.com.linkjob.dto.resume.UpdateResumeResDTO;
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

    @PutMapping
    @ApiMessage("Update Resume")
    public ResponseEntity<UpdateResumeResDTO> updateResume(@RequestBody @Valid UpdateResumeDTO request) {
        return ResponseEntity.ok()
                .body(resumeService.updateResume(request));
    }
}
