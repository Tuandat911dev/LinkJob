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
import vn.com.linkjob.domain.Resume;
import vn.com.linkjob.dto.paginate.ResultPaginationDTO;
import vn.com.linkjob.dto.resume.*;
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

    @DeleteMapping("/{id}")
    @ApiMessage("Delete Resume")
    public ResponseEntity<Void> deleteResume(@PathVariable("id") long id) {
        resumeService.deleteResume(id);
        return ResponseEntity.ok()
                .build();
    }

    @GetMapping("/{id}")
    @ApiMessage("Get resume by id")
    public ResponseEntity<ResumeResDTO> getResumeById(@PathVariable("id") long id) {
        return ResponseEntity.ok()
                .body(resumeService.getResumeById(id));
    }

    @GetMapping
    @ApiMessage("Get resumes with pagination")
    public ResponseEntity<ResultPaginationDTO> getResumesWithPagination(Pageable pageable,
                                                                        @Filter Specification<Resume> spec) {
        return ResponseEntity
                .ok()
                .body(resumeService.getResumesWithPaginate(pageable, spec));
    }
}
