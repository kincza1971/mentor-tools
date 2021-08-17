package mentortools.trainingclass;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/trainingclasses")
public class TrainingClassController {

    private TrainingClassService service;


    @GetMapping
    public List<TrainingClassDTO> getTrainingClasses(@RequestParam Optional<String> namePartOptional) {
        return service.getTrainingClasses(namePartOptional);
    }

    @PostMapping
    public TrainingClassDTO createLesson(@Valid @RequestBody CreateOrUpdateTrainingClassCommand command) {
        return service.createTrainingClass(command);
    }

    @PutMapping("/{id}")
    public TrainingClassDTO updateTrainingClass(@PathVariable long id, @Valid @RequestBody CreateOrUpdateTrainingClassCommand command) {
        return service.updateTrainingClass(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteTrainingClass(@PathVariable long id) {
        service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllTrainingClass(){
        service.deleteAll();
    }

}
