package vn.com.linkjob.dto.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.com.linkjob.util.enums.GenderEnum;

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
}