package vn.com.linkjob.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import vn.com.linkjob.domain.Skill;
import vn.com.linkjob.dto.skill.CreateSkillRequestDTO;
import vn.com.linkjob.dto.skill.SkillResponseDTO;
import vn.com.linkjob.dto.skill.UpdateSkillRequestDTO;

@Mapper(componentModel = "spring")
public interface SkillMapper {
    Skill toSkill(CreateSkillRequestDTO request);

    SkillResponseDTO toSkillResponseDTO(Skill skill);

    void updateSkill(@MappingTarget Skill skill, UpdateSkillRequestDTO request);
}
