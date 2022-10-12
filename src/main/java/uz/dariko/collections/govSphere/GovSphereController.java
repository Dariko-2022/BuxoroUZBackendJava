package uz.dariko.collections.govSphere;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.collections.govSphere.dto.GovSphereCreateDTO;

@RestController
@RequestMapping("api/admin/govSphere")
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
}
