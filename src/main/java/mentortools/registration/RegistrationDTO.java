package mentortools.registration;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mentortools.trainingclass.TrainingClassDTO;
import mentortools.student.StudentDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegistrationDTO {
    private Long id;

    private StudentDTO student;

    private TrainingClassDTO trainingClass;

    private RegistrationStatus status;
}
