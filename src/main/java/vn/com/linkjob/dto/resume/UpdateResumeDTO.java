package vn.com.linkjob.dto.resume;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.com.linkjob.util.enums.ResumeStatusEnum;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateResumeDTO {
    @NotNull(message = "ID không được để trống")
    private Long id;

    @NotNull(message = "Trạng thái không được để trống")
    private ResumeStatusEnum status;
}
