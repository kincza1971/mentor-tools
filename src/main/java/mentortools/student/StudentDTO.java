package mentortools.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mentortools.lessoncompletion.LessonCompletionDTO;
import mentortools.registration.RegistrationDTO;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private Long id;
    private String name;
    private String email;
    private String githubUser;
    private String description;

    Set<LessonCompletionDTO> lessons;

    List<RegistrationDTO> registrations;

}

