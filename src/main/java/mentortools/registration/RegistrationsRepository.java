package mentortools.registration;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationsRepository extends JpaRepository<Registration,Long> {
    List<Registration> findByStudentId(long id);
}
