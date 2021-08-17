package mentortools.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLessonCommand {

    @NotBlank
    @Length(min = 3, max = 255)
    private String name;

    @NotBlank
    private String url;


}
