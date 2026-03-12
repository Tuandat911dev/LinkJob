package vn.com.linkjob.util.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import vn.com.linkjob.exception.AppException;
import vn.com.linkjob.exception.ErrorCode;

public enum GenderEnum {
    MALE, FEMALE, OTHER;

    @JsonCreator
    public static GenderEnum fromString(String value) {
        if (value == null) return null;
        try {
            return GenderEnum.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AppException(ErrorCode.GENDER_INVALID);
        }
    }
}
