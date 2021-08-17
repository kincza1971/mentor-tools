package mentortools.module;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("api/modules")
public class ModuleController {

    private ModulesService service;


    @GetMapping
    public List<ModuleDTO> getModules(@RequestParam Optional<String> namePartOptional) {
        return service.getModules(namePartOptional);
    }

    @PostMapping
    public ModuleDTO createModule(@Valid @RequestBody CreateModuleCommand command) {
        return service.createModule(command);
    }

    @PutMapping("/{id}")
    public ModuleDTO updateModule(@PathVariable long id, UpdateModuleCommand command) {
        return service.updateModule(id, command);
    }
}
