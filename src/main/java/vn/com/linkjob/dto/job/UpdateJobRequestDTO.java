package vn.com.linkjob.dto.job;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateJobRequestDTO {
    @NotBlank(message = "Skill id không được để trống")
    long id;
    @NotBlank(message = "Tên skill không được để trống")
    String name;
}
