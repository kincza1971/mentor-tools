package mentortools.trainingclass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mentortools.exceptions.InvalidDataInCommandException;
import mentortools.registration.Registration;
import mentortools.syllabus.Syllabus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "classes")
public class TrainingClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate startDate;

    @NotNull

    private LocalDate endDate;

    private String description;

    @ManyToOne
    private Syllabus syllabus;

    @OneToMany(
            mappedBy = "trainingClass",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Registration> registrations;

    public TrainingClass(String name, LocalDate startDate, LocalDate endDate, String description) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
    }

    @PreUpdate
    @PrePersist
    public void checkDates() {
        if (!getEndDate().isAfter(getStartDate())) {
            throw new InvalidDataInCommandException("api/trainingclasses",
                    "Invalid Data In Command",
                    String.format("End date (%tF) has to be later than start date(%tF)", endDate, startDate));
        }
    }

}
