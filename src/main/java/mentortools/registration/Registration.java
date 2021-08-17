package mentortools.registration;

import com.fasterxml.jackson.annotation.JsonBackReference;
import mentortools.trainingclass.TrainingClass;
import mentortools.student.Student;

import javax.persistence.*;

@Entity
@Table(name = "registrations")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    @ManyToOne()
    @JoinColumn(name = "class_id")
    @JsonBackReference
    private TrainingClass trainingClass;

    @Enumerated(value = EnumType.STRING)
    private RegistrationStatus status;
}
