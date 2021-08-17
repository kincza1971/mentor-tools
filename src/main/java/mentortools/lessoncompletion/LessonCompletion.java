package mentortools.lessoncompletion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mentortools.lessoncompletion.CompletionStatus;
import mentortools.lesson.Lesson;
import mentortools.student.Student;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "completions")
public class LessonCompletion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private CompletionStatus videoStatus;
    private CompletionStatus practiceStatus;
    private LocalDate videoDate;
    private LocalDate practiceDate;
    private String commitUrl;
}
