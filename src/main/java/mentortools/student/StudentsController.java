package mentortools.student;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/students")
public class StudentsController {

    private StudentsService service;


    @GetMapping
    public List<StudentDTO> getStudents(@RequestParam Optional<String> namePartOptional) {
        return service.getStudents(namePartOptional);
    }

    @PostMapping
    public StudentDTO createStudent(@Valid @RequestBody CreateStudentCommand command) {
        return service.createStudent(command);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudent(@PathVariable long id, UpdateStudentCommand command) {
        return service.updateStudent(id, command);
    }
}
