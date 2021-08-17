package mentortools.syllabus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mentortools.trainingclass.TrainingClass;
import mentortools.module.Module;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Syllabus {
    @Id
    private Long id;

    private String name;

    @OneToMany(
            mappedBy = "syllabus",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<TrainingClass> classes;

    @ManyToMany
    @JoinTable(name = "syllabus_modules",
            joinColumns=@JoinColumn(name = "syllabus_id"),
            inverseJoinColumns = @JoinColumn(name = "modul_id")
    )
    private Set<Module> modules;
}
