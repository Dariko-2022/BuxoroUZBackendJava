package uz.dariko.collections.informations;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.collections.informations.dto.InformationCreateDTO;
import uz.dariko.collections.informations.dto.InformationDTO;
import uz.dariko.collections.informations.dto.InformationUpdateDTO;

import java.util.UUID;

@RestController
@RequestMapping("information")
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
    public ResponseEntity<?> delete(@PathVariable("code") UUID code) {
        return service.delete(code);
    }

    @Override
    public ResponseEntity<?> get(@PathVariable("code") UUID code) {
        return service.get(code);
    }

    @Override
    public ResponseEntity<?> list() {
        return service.list();
    }


    @GetMapping("getBySubmenuId")
    public ResponseEntity<?> getPageable(
            @RequestParam(name = "size") int size,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "code") UUID id
    ){
        return service.getBySubmenuId(PageRequest.of(page,size),id);
    }

    @GetMapping("listByInfoGroup/{infoGroupID}")
    public ResponseEntity<?> listByInfoGroup(@PathVariable UUID infoGroupID){
        return service.listByInfoGroup(infoGroupID);
    }
}
