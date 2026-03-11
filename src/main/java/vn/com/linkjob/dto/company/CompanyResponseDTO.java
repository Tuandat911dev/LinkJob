package vn.com.linkjob.dto.company;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponseDTO {
    long id;

    String name;

    String description;

    String address;

    String logo;

    Instant createdAt;

    Instant updatedAt;

    String createdBy;

    String updatedBy;
}
