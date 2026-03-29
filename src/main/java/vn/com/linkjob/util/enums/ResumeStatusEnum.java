package vn.com.linkjob.util.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import vn.com.linkjob.exception.AppException;
import vn.com.linkjob.exception.ErrorCode;

public enum ResumeStatusEnum {
    PENDING,
    REVIEWING,
    APPROVED,
    REJECTED;

    @JsonCreator
    public static ResumeStatusEnum fromString(String value) {
        if (value == null) return null;
        try {
            return ResumeStatusEnum.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.RESUME_STATUS_INVALID);
        }
    }
}
