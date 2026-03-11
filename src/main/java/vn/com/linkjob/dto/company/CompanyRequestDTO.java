package vn.com.linkjob.dto.company;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRequestDTO {
    @NotBlank(message = "Tên không được để trống")
    String name;

    @NotBlank(message = "Mô tả không được để trống")
    String description;

    @NotBlank(message = "Địa chỉ không được để trống")
    String address;

    @NotBlank(message = "Logo không được để trống")
    String logo;
}
