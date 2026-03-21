package vn.com.linkjob.dto.skill;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SkillResponseDTO {
    long id;
    String name;
    Instant createdAt;
    Instant updatedAt;
    String createdBy;
    String updatedBy;
}
