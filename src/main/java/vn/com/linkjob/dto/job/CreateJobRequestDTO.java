package vn.com.linkjob.dto.job;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.com.linkjob.util.annotation.StartBeforeEnd;
import vn.com.linkjob.util.enums.JobLevelEnum;

import java.time.Instant;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@StartBeforeEnd
public class CreateJobRequestDTO {
    @NotBlank(message = "Tên công việc không được để trống")
    String name;

    @NotBlank(message = "Vị trí không được để trống")
    String location;

    @NotBlank(message = "Mô tả không được để trống")
    String description;

    @Positive(message = "Lương phải lớn hơn 0")
    double salary;

    @Min(value = 1, message = "Số lượng tuyển dụng ít nhất là 1")
    int quantity;

    @NotNull(message = "Trình độ (level) là bắt buộc")
    JobLevelEnum level;

    @NotNull(message = "Ngày bắt đầu không được để trống")
    @FutureOrPresent(message = "Ngày bắt đầu không được là ngày quá khứ")
    Instant startDate;

    @NotNull(message = "Ngày kết thúc không được để trống")
    Instant endDate;

    boolean active;

    @NotNull(message = "ID công ty không được để trống")
    long companyId;

    CreateJobSkillDTO skills;

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CreateJobSkillDTO {
        @Valid
        @NotNull(message = "Danh sách kỹ năng không được để trống")
        List<Long> skillId;
    }
}
