package mentortools.validations;


import mentortools.trainingclass.CreateOrUpdateTrainingClassCommand;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TrainingClassDateValidator implements ConstraintValidator<ValidTrainingClassDate, CreateOrUpdateTrainingClassCommand> {

    @Override
    public void initialize(ValidTrainingClassDate constraint) {
        ConstraintValidator.super.initialize(constraint);
    }

    public boolean isValid(CreateOrUpdateTrainingClassCommand command, ConstraintValidatorContext context) {
        return (command.getEndDate() == null ||
                command.getEndDate() == null ||
                command.getStartDate().isBefore(command.getEndDate()));
    }
}