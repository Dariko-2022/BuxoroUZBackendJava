package uz.dariko.collections.govSphere;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.collections.govSphere.dto.GovSphereCreateDTO;

@RestController
@RequestMapping("govSphere")
public class GovSphereController extends AbstractController<GovSphereService> {


    public GovSphereController(GovSphereService service) {
        super(service);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody GovSphereCreateDTO govSphereCreateDto
            ) {

        return service.create(govSphereCreateDto);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }
}
