package vn.com.linkjob.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.com.linkjob.util.enums.GenderEnum;

import java.time.Instant;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
    Long id;
    String name;
    String email;
    GenderEnum gender;
    int age;
    String address;
    CompanyUserResponse company;
    Instant createdAt;
    Instant updatedAt;
    String createdBy;
    String updatedBy;

    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CompanyUserResponse {
        long id;
        String name;
    }
}