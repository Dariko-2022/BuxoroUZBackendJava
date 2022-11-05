package uz.dariko.collections.newsSphere;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.collections.newsSphere.dto.NewsSphereCreateDTO;
import uz.dariko.collections.newsSphere.dto.NewsSphereDTO;
import uz.dariko.collections.newsSphere.dto.NewsSphereUpdateDTO;

import java.util.UUID;


@RestController
@RequestMapping("newsSphere")
public class NewsSphereController extends AbstractController<NewsSphereService> implements GenericCRUDController<NewsSphereCreateDTO, NewsSphereUpdateDTO, NewsSphereDTO, UUID> {

    public NewsSphereController(NewsSphereService service) {
        super(service);
    }

    @Override
    public ResponseEntity<?> create(NewsSphereCreateDTO DTO) throws Exception {
        return service.create(DTO);
    }

    @Override
    public ResponseEntity<?> update(NewsSphereUpdateDTO DTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(UUID code) {
        return null;
    }

    @Override
    public ResponseEntity<?> get(UUID code) {
        return null;
    }

    @Override
    public ResponseEntity<?> list() {
        return service.getList();
    }
}
