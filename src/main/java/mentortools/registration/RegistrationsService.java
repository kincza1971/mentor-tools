package mentortools.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import mentortools.student.Student;
import mentortools.student.StudentsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class RegistrationsService {
    private RegistrationsRepository repository;
    private StudentsRepository studentsRepository;
    private ModelMapper modelMapper;

    public List<StudentRegistrationDTO> getRegistrationByStudent(long id) {
        return repository.findByStudentId(id)
                        .stream()
                        .map(r -> modelMapper.map(r, StudentRegistrationDTO.class))
                        .toList();
    }


    public List<RegisteredStudentDTO> getStudentsByRegistration(long id) {
        return  studentsRepository.findStudentCustom(id);
    }

    private RegisteredStudentDTO getRegisteredStudentDTO(Object[] o) {
        return new RegisteredStudentDTO(
                (long) o[0],
                (String) o[1],
                (RegistrationStatus) o[2]);
    }
}
