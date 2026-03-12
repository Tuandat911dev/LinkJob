package vn.com.linkjob.dto.paginate;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultPaginationDTO {
    Meta meta;
    Object result;
}
