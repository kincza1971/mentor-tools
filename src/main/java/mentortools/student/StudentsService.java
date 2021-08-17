package mentortools.student;

import mentortools.exceptions.MentorEntityNotFoundException;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class StudentsService {

    private StudentsRepository repository;
    private ModelMapper modelMapper;

    public StudentsService(StudentsRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<StudentDTO> getStudents(Optional<String> namePartOptional) {
        List<Student> found;
        if (namePartOptional.isPresent()) {
            String name = getLikeString(namePartOptional);
            found = repository.findByNameLike(name);
        } else {
            found = repository.findAll();
        }

        return found
                .stream()
                .map(s -> modelMapper.map(s, StudentDTO.class))
                .toList();
    }

    private String getLikeString(Optional<String> namePartOptional) {
        String name = namePartOptional.get();
        if (name.startsWith("%") || name.endsWith("%")) {
            return name;
        }
        else {
            return "%" + name + "%";
        }
    }


    @Transactional
    public StudentDTO createStudent(CreateStudentCommand command) {
        Student student =
                new Student(command.getName(),command.getEmail(), command.getGithubUser(), command.getDescription());
        repository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }

    @Transactional
    public StudentDTO updateStudent(long id, UpdateStudentCommand command) {
        Student student = repository.findById(id)
                .orElseThrow(() -> new MentorEntityNotFoundException(
                        "/api/student/not-found",
                        "Student Not Found",
                        "Student not found with this id: " + id
                ));
        student = setStudentValues(command, student);
        repository.save(student);
        return modelMapper.map(student, StudentDTO.class);
    }


    private Student setStudentValues(UpdateStudentCommand command, Student student) {

        String name = command.getName();
        if (isValidString(name)) {
            student.setName(name);
        }

        String email = command.getEmail();
        if (new EmailValidator().isValid(email, null)) {
            student.setEmail(email);
        }

        String gitUser = command.getGithubUser();
        if (isValidString(gitUser)) {
            student.setGithubUser(gitUser);
        }

        String description = command.getDescription();
        if (isValidString(description)) {
            student.setGithubUser(gitUser);
        }

        return student;
    }

    private boolean isValidString(String toCheck) {
        return toCheck != null && toCheck.length() >2 && !toCheck.isBlank();
    }

}
