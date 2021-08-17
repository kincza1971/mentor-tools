package mentortools.validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Constraint(validatedBy = TrainingClassDateValidator.class)
public @interface ValidTrainingClassDate {

    String message() default "If both date is added then end date has to be later than start date";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}