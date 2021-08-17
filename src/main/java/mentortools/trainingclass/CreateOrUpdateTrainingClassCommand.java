package mentortools.trainingclass;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mentortools.exceptions.InvalidDataInCommandException;
import mentortools.validations.ValidTrainingClassDate;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ValidTrainingClassDate
public class CreateOrUpdateTrainingClassCommand {

    @NotBlank
    @Length(min = 3, max = 255)
    private String name;

    @Schema(description = "Start date of training, not null",implementation = String.class, example = "2000-12-31")
    private LocalDate startDate;

    @Schema(description = "End date of training not null, and later than start date",implementation = String.class, example = "2000-12-31")
    private LocalDate endDate;


    private String description;

}
