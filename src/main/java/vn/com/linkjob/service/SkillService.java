package vn.com.linkjob.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import vn.com.linkjob.domain.Skill;
import vn.com.linkjob.dto.paginate.ResultPaginationDTO;
import vn.com.linkjob.dto.skill.CreateSkillRequestDTO;
import vn.com.linkjob.dto.skill.SkillResponseDTO;
import vn.com.linkjob.dto.skill.UpdateSkillRequestDTO;
import vn.com.linkjob.exception.AppException;
import vn.com.linkjob.exception.ErrorCode;
import vn.com.linkjob.mapper.SkillMapper;
import vn.com.linkjob.repository.SkillRepository;

import java.util.List;
import java.util.Optional;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class SkillService {
    SkillRepository skillRepository;
    SkillMapper skillMapper;

    public SkillResponseDTO createSkill(CreateSkillRequestDTO request) {
        if (skillRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.SKILL_EXISTED);
        } else {
            return skillMapper.toSkillResponseDTO(skillRepository.save(skillMapper.toSkill(request)));
        }
    }

    public ResultPaginationDTO getSkillsWithPaginate(Pageable pageable, Specification<Skill> spec) {
        Page<Skill> skills = skillRepository.findAll(spec, pageable);
        List<SkillResponseDTO> result = skills.getContent().stream()
                .map(skillMapper::toSkillResponseDTO)
                .toList();

        return ResultPaginationDTO.builder()
                .meta(ResultPaginationDTO.Meta.builder()
                        .pageSize(skills.getSize())
                        .page(skills.getNumber() + 1)
                        .total(skills.getTotalElements())
                        .pages(skills.getTotalPages())
                        .build())
                .result(result)
                .build();
    }

    public SkillResponseDTO updateSkill(UpdateSkillRequestDTO request) {
        Skill skill = skillRepository.findById(request.getId()).orElseThrow(
                () -> new AppException(ErrorCode.SKILL_NOT_EXIST)
        );
        skill.setName(request.getName());

        return skillMapper.toSkillResponseDTO(skillRepository.save(skill));
    }

    public Optional<Skill> getSkillById(long id) {
        return skillRepository.findById(id);
    }

    public void deleteSkill(long id) {
        Skill skill = skillRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.SKILL_NOT_EXIST)
        );

        try {
            skillRepository.delete(skill);
        } catch (Exception e) {
            throw new AppException(ErrorCode.SKILL_IN_USE);
        }
    }
}
