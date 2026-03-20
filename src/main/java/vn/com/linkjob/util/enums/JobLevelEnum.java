package vn.com.linkjob.util.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import vn.com.linkjob.exception.AppException;
import vn.com.linkjob.exception.ErrorCode;

public enum JobLevelEnum {
    INTERN, FRESHER, JUNIOR, SENIOR, MANAGER;

    @JsonCreator
    public static JobLevelEnum fromString(String value) {
        if (value == null) return null;
        try {
            return JobLevelEnum.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.JOB_LEVEL_INVALID);
        }
    }
}
