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
import vn.com.linkjob.domain.Skill;
import vn.com.linkjob.dto.paginate.ResultPaginationDTO;
import vn.com.linkjob.dto.skill.CreateSkillRequestDTO;
import vn.com.linkjob.dto.skill.SkillResponseDTO;
import vn.com.linkjob.dto.skill.UpdateSkillRequestDTO;
import vn.com.linkjob.service.SkillService;
import vn.com.linkjob.util.annotation.ApiMessage;

@RestController
@RequestMapping("/api/v1/skills")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class SkillController {
    SkillService skillService;

    @PostMapping
    @ApiMessage("create new skill")
    public ResponseEntity<SkillResponseDTO> createNewSkill(@RequestBody @Valid CreateSkillRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(skillService.createSkill(request));
    }

    @PutMapping
    @ApiMessage("edit skill")
    public ResponseEntity<SkillResponseDTO> updateSkill(
            @RequestBody UpdateSkillRequestDTO request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(skillService.updateSkill(request));
    }

//    @DeleteMapping("/{id}")
//    @ApiMessage("delete skill")
//    public ResponseEntity<Void> deleteSkill(@PathVariable long id) {
//        skillService.deleteSkill(id);
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .build();
//    }

    @GetMapping
    @ApiMessage("get skill with paginate, sort, filter")
    public ResponseEntity<ResultPaginationDTO> getCompaniesWithPagination(Pageable pageable,
                                                                          @Filter Specification<Skill> spec) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(skillService.getSkillsWithPaginate(pageable, spec));
    }
}
