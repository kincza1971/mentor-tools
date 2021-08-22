package mentortools.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredStudentDTO {
    private long studentId;
    private String StudentName;
    private RegistrationStatus status;
}
