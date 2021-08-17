package mentortools.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mentortools.lessoncompletion.LessonCompletion;
import mentortools.registration.Registration;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String githubUser;

    private String description;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<LessonCompletion> completions;

    @OneToMany(
            mappedBy = "student",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Registration> registrations;

    public Student(String name, String email, String githubUser, String description) {
        this.name = name;
        this.email = email;
        this.githubUser = githubUser;
        this.description = description;
    }
}
