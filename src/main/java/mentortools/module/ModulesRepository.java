package mentortools.module;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ModulesRepository extends JpaRepository<Module, Long> {
    List<Module> findByNameLike(String s);
}
