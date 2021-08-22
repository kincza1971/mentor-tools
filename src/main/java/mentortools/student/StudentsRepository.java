package mentortools.student;

import mentortools.registration.RegisteredStudentDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentsRepository extends JpaRepository<Student, Long> {
    List<Student> findByNameLike(String s);

    @Query(value =
            "select new mentortools.registration.RegisteredStudentDTO(s.id, s.name, r.status) from Student s, Registration r, TrainingClass t " +
                    "where s.id = r.student.id and r.trainingClass.id = t.id and t.id = ?1")
    List<RegisteredStudentDTO> findStudentCustom(long id);

}
