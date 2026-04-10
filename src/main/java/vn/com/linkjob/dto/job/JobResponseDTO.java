package vn.com.linkjob.dto.job;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.com.linkjob.util.enums.JobLevelEnum;

import java.time.Instant;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobResponseDTO {
    long id;
    String name;
    double salary;
    int quantity;
    JobLevelEnum level;
    Instant startDate;
    Instant endDate;
    boolean active;
    String description;
    String location;
    List<JobSkillResponseDTO> skills;
    JobCompanyResponseDTO company;

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JobSkillResponseDTO {
        long id;
        String name;
    }

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JobCompanyResponseDTO {
        long id;
        String name;
        String logo;
    }

    Instant createdAt;
    Instant updatedAt;
    String createdBy;
    String updatedBy;
}
