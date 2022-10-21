package uz.dariko.collections.sphere;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.base.dto.BaseOrderDTO;
import uz.dariko.collections.sphere.dto.SphereCreateDTO;
import uz.dariko.collections.sphere.dto.SphereDTO;
import uz.dariko.collections.sphere.dto.SphereUpdateDTO;
import uz.dariko.response.Data;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("sphere/")
public class SphereController extends AbstractController<SphereService> implements GenericCRUDController<SphereCreateDTO, SphereUpdateDTO, SphereDTO, UUID>
{


    public SphereController(SphereService service) {
        super(service);
    }
    @Override
    public ResponseEntity<?> create(SphereCreateDTO sphereCreateDto) {
        return service.create(sphereCreateDto);
    }

    @Override
    public ResponseEntity<?> update(SphereUpdateDTO DTO) {
        return service.update(DTO);
    }

    @Override
    public ResponseEntity<?> delete(UUID id) {
        return service.delete(id);
    }

    @Override
    public ResponseEntity<?> get(UUID id) {
        return service.get(id);
    }


    @Override
    public ResponseEntity<?> list() {
        return service.getAll();
    }

    @RequestMapping("changeOrder")
    public ResponseEntity<Data<List<SphereDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
        return service.changeOrder(baseOrderDTO);
    }


}
