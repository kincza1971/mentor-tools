package mentortools.lesson;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/lessons")
public class LessonController {

    private LessonsService service;


    @GetMapping
    public List<LessonDTO> getLessons(@RequestParam Optional<String> namePartOptional) {
        return service.getLessons(namePartOptional);
    }

    @PostMapping
    public LessonDTO createLesson(@Valid @RequestBody CreateLessonCommand command) {
        return service.createLesson(command);
    }

    @PutMapping("/{id}")
    public LessonDTO updateLesson(@PathVariable long id, UpdateLessonCommand command) {
        return service.updateLesson(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteLesson(@PathVariable long id) {
        service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllLesson(){
        service.deleteAll();
    }
}
