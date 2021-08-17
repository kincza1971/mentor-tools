package mentortools.trainingclass;

import mentortools.lesson.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrainingClassRepository extends JpaRepository<TrainingClass, Long> {
    List<TrainingClass> findByNameLike(String name);
}
