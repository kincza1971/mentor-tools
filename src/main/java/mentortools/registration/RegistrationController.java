package mentortools.registration;

import lombok.AllArgsConstructor;
import mentortools.student.StudentDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RegistrationController {

    private RegistrationsService service;

    @GetMapping("/students/{id}/registrations")
    public List<StudentRegistrationDTO> getRegistrationsByStudent(@PathVariable long id) {
        return service.getRegistrationByStudent(id);
    }

    @GetMapping("/trainingclasses/{id}/registrations")
    public List<RegisteredStudentDTO> getStudentsByRegistration(@PathVariable long id) {
        return service.getStudentsByRegistration(id);
    }

}
