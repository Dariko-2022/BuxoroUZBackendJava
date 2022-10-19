package uz.dariko.collections.informations;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.collections.informations.dto.InformationCreateDTO;
import uz.dariko.collections.informations.dto.InformationDTO;
import uz.dariko.collections.informations.dto.InformationUpdateDTO;

import java.util.UUID;

@RestController
@RequestMapping("information/")
public class InformationController extends AbstractController<InformationService> implements GenericCRUDController<InformationCreateDTO, InformationUpdateDTO, InformationDTO, UUID> {
    public InformationController(InformationService service) {
        super(service);
    }

    @Override
    public ResponseEntity<?> create(InformationCreateDTO DTO) throws Exception {
        return service.create(DTO);
    }

    @Override
    public ResponseEntity<?> update(InformationUpdateDTO DTO) {
        return service.update(DTO);
    }

    @Override
    public ResponseEntity<?> delete(UUID code) {
        return service.delete(code);
    }

    @Override
    public ResponseEntity<?> get(UUID code) {
        return service.get(code);
    }

    @Override
    public ResponseEntity<?> list() {
        return service.list();
    }

    @GetMapping("listByInfoGroup/{infoGroupID}")
    public ResponseEntity<?> listByInfoGroup(@PathVariable UUID infoGroupID){
        return service.listByInfoGroup(infoGroupID);
    }
}
