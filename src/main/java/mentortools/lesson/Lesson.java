package mentortools.lesson;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mentortools.lessoncompletion.LessonCompletion;
import mentortools.module.Module;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String url;

    @OneToMany(
            mappedBy = "lesson",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonBackReference
    private Set<LessonCompletion> completions;

    @ManyToMany()
    @JoinTable(name = "module_lessons",
            joinColumns= @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name ="module_id")
    )
    @JsonBackReference
    private Set<Module> modules;

    public Lesson(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public void addModule(Module module) {
        modules.add(module);
    }
}
