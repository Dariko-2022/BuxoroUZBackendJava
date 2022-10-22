package uz.dariko.collections.govGroup;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.base.dto.BaseOrderDTO;
import uz.dariko.collections.govGroup.dto.GovGroupCreateDTO;
import uz.dariko.collections.govGroup.dto.GovGroupDTO;
import uz.dariko.collections.govGroup.dto.GovGroupUpdateDTO;
import uz.dariko.response.Data;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("govGroup")
public class GovGroupController extends AbstractController<GovGroupService> implements GenericCRUDController<GovGroupCreateDTO, GovGroupUpdateDTO, GovGroupDTO, UUID> {
    public GovGroupController(GovGroupService service) {
        super(service);
    }

    @Override
    public ResponseEntity<?> create(GovGroupCreateDTO DTO) throws Exception {
        return service.create(DTO);
    }

    @Override
    public ResponseEntity<?> update(GovGroupUpdateDTO DTO) {
        return service.update(DTO);
    }

    @Override
    public ResponseEntity<?> delete(
            @PathVariable("code") UUID code) {
        return service.delete(code);
    }

    @Override
    public ResponseEntity<?> get(
            @PathVariable("code") UUID code) {
        return service.getById(code);
    }

    @Override
    public ResponseEntity<?> list() {
        return service.getList();
    }


    @RequestMapping("changeOrder")
    public ResponseEntity<Data<List<GovGroupDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
        return service.changeOrder(baseOrderDTO);
    }
}
