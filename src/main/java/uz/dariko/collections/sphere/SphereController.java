package uz.dariko.collections.sphere;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.collections.sphere.dto.SphereCreateDTO;

@RestController
@RequestMapping("sphere/")
public class SphereController extends AbstractController<SphereService> {


    public SphereController(SphereService service) {
        super(service);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @RequestBody SphereCreateDTO sphereCreateDto
    ) {
        return service.create(sphereCreateDto);
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        return service.getAll();
    }

}
