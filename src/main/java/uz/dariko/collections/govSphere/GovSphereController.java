package uz.dariko.collections.govSphere;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.collections.govSphere.dto.GovSphereCreateDTO;
import uz.dariko.collections.govSphere.dto.GovSphereDTO;
import uz.dariko.collections.govSphere.dto.GovSphereUpdateDTO;

import java.util.UUID;

@RestController
@RequestMapping("govSphere")
public class GovSphereController extends AbstractController<GovSphereService> implements GenericCRUDController<GovSphereCreateDTO, GovSphereUpdateDTO, GovSphereDTO, UUID> {


    public GovSphereController(GovSphereService service) {
        super(service);
    }

    @PostMapping
    public ResponseEntity<?> create(GovSphereCreateDTO govSphereCreateDto) {
        return service.create(govSphereCreateDto);
    }

    @Override
    public ResponseEntity<?> update(GovSphereUpdateDTO DTO) {
        return service.update(DTO);
    }

    @Override
    public ResponseEntity<?> delete(UUID id) {
        return null;
    }

    @Override
    public ResponseEntity<?> get(UUID id) {
        return service.get(id);
    }

    @Override
    public ResponseEntity<?> list() {
        return service.getAll();
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }
}
