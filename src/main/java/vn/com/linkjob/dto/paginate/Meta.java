package vn.com.linkjob.dto.paginate;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meta {
    int page;
    int pageSize;
    int pages;
    long total;
}
