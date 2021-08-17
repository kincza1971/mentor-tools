package mentortools.lessoncompletion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LessonCompletionDTO {
    private Long id;
    private long LessonDTO;
    private long StudentDTO;
    private CompletionStatus videStatus;
    private CompletionStatus practiceStatus;
    private LocalDate videoDate;
    private LocalDate practiceDate;
    private String githubUrl;
}
