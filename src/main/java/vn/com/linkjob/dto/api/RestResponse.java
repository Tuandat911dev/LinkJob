package vn.com.linkjob.dto.api;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestResponse<T> {
    int statusCode;
    String status;
    String error;
    Object message;
    T data;
}
