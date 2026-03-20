package vn.com.linkjob.util.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import vn.com.linkjob.dto.job.CreateJobRequestDTO;

public class StartBeforeEndValidator implements ConstraintValidator<StartBeforeEnd, CreateJobRequestDTO> {
    @Override
    public boolean isValid(CreateJobRequestDTO dto, ConstraintValidatorContext context) {
        if (dto.getStartDate() == null || dto.getEndDate() == null) {
            return true;
        }

        return dto.getStartDate().isBefore(dto.getEndDate());
    }
}
