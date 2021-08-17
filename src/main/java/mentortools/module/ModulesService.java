package mentortools.module;

import mentortools.exceptions.MentorEntityNotFoundException;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ModulesService {

    private ModulesRepository repository;
    private ModelMapper modelMapper;

    public ModulesService(ModulesRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<ModuleDTO> getModules(Optional<String> namePartOptional) {
        List<Module> found;
        if (namePartOptional.isPresent()) {
            String name = getLikeString(namePartOptional);
            found = repository.findByNameLike(name);
        } else {
            found = repository.findAll();
        }

        return found
                .stream()
                .map(s -> modelMapper.map(s, ModuleDTO.class))
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
    public ModuleDTO createModule(CreateModuleCommand command) {
        Module module =
                new Module(command.getName(),command.getUrl());
        repository.save(module);
        return modelMapper.map(module, ModuleDTO.class);
    }

    @Transactional
    public ModuleDTO updateModule(long id, UpdateModuleCommand command) {
        Module module = repository.findById(id)
                .orElseThrow(() -> new MentorEntityNotFoundException(
                        "/api/module/not-found",
                        "module Not Found",
                        "module not found with this id: " + id
                ));
        module = setModuleValues(command, module);
        repository.save(module);
        return modelMapper.map(module, ModuleDTO.class);
    }


    private Module setModuleValues(UpdateModuleCommand command, Module module) {

        String name = command.getName();
        if (isValidString(name)) {
            module.setName(name);
        }

        String url = command.getUrl();
        if (isValidString(url)) {
            module.setUrl(url);
        }
        return module;
    }

    private boolean isValidString(String toCheck) {
        return toCheck != null && toCheck.length() >2 && !toCheck.isBlank();
    }

}
