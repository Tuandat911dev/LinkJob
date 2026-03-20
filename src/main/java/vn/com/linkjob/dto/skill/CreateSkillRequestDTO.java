package vn.com.linkjob.dto.skill;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateSkillRequestDTO {
    @NotBlank(message = "Tên skill không được để trống")
    String name;
}
