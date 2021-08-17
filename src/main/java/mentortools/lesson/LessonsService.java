package mentortools.lesson;

import mentortools.exceptions.MentorEntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LessonsService {

    private LessonsRepository repository;
    private ModelMapper modelMapper;

    public LessonsService(LessonsRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<LessonDTO> getLessons(Optional<String> namePartOptional) {
        List<Lesson> found;
        if (namePartOptional.isPresent()) {
            String name = getLikeString(namePartOptional);
            found = repository.findByNameLike(name);
        } else {
            found = repository.findAll();
        }

        return found
                .stream()
                .map(s -> modelMapper.map(s, LessonDTO.class))
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
    public LessonDTO createLesson(CreateLessonCommand command) {
        Lesson lesson =
                new Lesson(command.getName(),command.getUrl());
        repository.save(lesson);
        return modelMapper.map(lesson, LessonDTO.class);
    }

    @Transactional
    public LessonDTO updateLesson(long id, UpdateLessonCommand command) {
        Lesson lesson = repository.findById(id)
                .orElseThrow(() -> new MentorEntityNotFoundException(
                        "/api/Lesson/not-found",
                        "Lesson Not Found",
                        "Lesson not found with this id: " + id
                ));
        lesson = setLessonValues(command, lesson);
        repository.save(lesson);
        return modelMapper.map(lesson, LessonDTO.class);
    }


    private Lesson setLessonValues(UpdateLessonCommand command, Lesson lesson) {

        String name = command.getName();
        if (isValidString(name)) {
            lesson.setName(name);
        }

        String url = command.getUrl();
        if (isValidString(url)) {
            lesson.setUrl(url);
        }
        return lesson;
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
