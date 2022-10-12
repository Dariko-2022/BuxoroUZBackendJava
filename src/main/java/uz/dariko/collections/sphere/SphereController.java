package uz.dariko.collections.sphere;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.collections.sphere.dto.SphereCreateDTO;

@RestController
@RequestMapping("api/admin/sphere")
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

}
