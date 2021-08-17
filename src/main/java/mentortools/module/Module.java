package mentortools.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mentortools.lessoncompletion.LessonCompletion;
import mentortools.registration.Registration;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "modules")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String url;

    public Module(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
