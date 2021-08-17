package mentortools.lesson;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonsRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByNameLike(String namePartString);
}
