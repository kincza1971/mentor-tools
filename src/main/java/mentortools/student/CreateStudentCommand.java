package mentortools.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentCommand {

    @NotBlank
    @Length(min = 3, max = 255)
    private String name;

    @Email
    @NotBlank
    private String email;

    private String githubUser;

    private String description;

}
