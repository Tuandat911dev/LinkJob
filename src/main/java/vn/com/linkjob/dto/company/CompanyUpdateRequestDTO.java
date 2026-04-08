package vn.com.linkjob.dto.company;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompanyUpdateRequestDTO {
    @NotNull(message = "ID không được để trống")
    Long id;

    @NotBlank(message = "Tên không được để trống")
    String name;

    @NotBlank(message = "Mô tả không được để trống")
    String description;

    @NotBlank(message = "Địa chỉ không được để trống")
    String address;

    @NotBlank(message = "Logo không được để trống")
    String logo;
}
