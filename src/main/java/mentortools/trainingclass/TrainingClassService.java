package mentortools.trainingclass;

import mentortools.exceptions.InvalidDataInCommandException;
import mentortools.exceptions.MentorEntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingClassService {

    private TrainingClassRepository repository;
    private ModelMapper modelMapper;

    public TrainingClassService(TrainingClassRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<TrainingClassDTO> getTrainingClasses(Optional<String> namePartOptional) {
        List<TrainingClass> found;
        if (namePartOptional.isPresent()) {
            String name = getLikeString(namePartOptional);
            found = repository.findByNameLike(name);
        } else {
            found = repository.findAll();
        }

        return found
                .stream()
                .map(s -> modelMapper.map(s, TrainingClassDTO.class))
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
    public TrainingClassDTO createTrainingClass(CreateOrUpdateTrainingClassCommand command) {
        TrainingClass trainingClass =
                new TrainingClass(command.getName(), command.getStartDate(), command.getEndDate(), command.getDescription() );
        repository.save(trainingClass);
        return modelMapper.map(trainingClass, TrainingClassDTO.class);
    }

    @Transactional
    public TrainingClassDTO updateTrainingClass(long id, CreateOrUpdateTrainingClassCommand command) {
        TrainingClass trainingClass = repository.findById(id)
                .orElseThrow(() -> new MentorEntityNotFoundException(
                        "/api/trainingclasses/not-found",
                        "TrainingClass Not Found",
                        "TrainingClass not found with this id: " + id
                ));
        trainingClass = setTrainingClassValues(command, trainingClass);
        System.out.println(trainingClass);
        repository.save(trainingClass);
        return modelMapper.map(trainingClass, TrainingClassDTO.class);
    }


    private TrainingClass setTrainingClassValues(CreateOrUpdateTrainingClassCommand command, TrainingClass trainingClass) {

        String name = command.getName();
        trainingClass.setName(name);

        LocalDate startDate = command.getStartDate();
        trainingClass.setStartDate(startDate);

        LocalDate endDate = command.getEndDate();
        trainingClass.setEndDate(endDate);

        String description = command.getDescription();
        trainingClass.setDescription(description);

        return trainingClass;
    }

    private boolean isValidString(String toCheck) {
        return toCheck != null && toCheck.length() >2 && !toCheck.isBlank();
    }

    @Transactional
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
