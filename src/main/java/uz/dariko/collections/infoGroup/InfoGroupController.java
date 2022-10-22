package uz.dariko.collections.infoGroup;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.base.dto.BaseOrderDTO;
import uz.dariko.collections.infoGroup.dto.InfoGroupCreateDTO;
import uz.dariko.collections.infoGroup.dto.InfoGroupDTO;
import uz.dariko.collections.infoGroup.dto.InfoGroupUpdateDTO;
import uz.dariko.collections.sphere.dto.ChangeMenuDTO;
import uz.dariko.response.Data;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("infoGroup")
public class InfoGroupController extends AbstractController<InfoGroupService> implements GenericCRUDController<InfoGroupCreateDTO, InfoGroupUpdateDTO, InfoGroupDTO, UUID> {
    public InfoGroupController(InfoGroupService service) {
        super(service);
    }

    @Override
    public ResponseEntity<?> create(InfoGroupCreateDTO DTO) {
        return service.create(DTO);
    }

    @Override
    public ResponseEntity<?> update(InfoGroupUpdateDTO DTO) {
        return service.update(DTO);
    }

    @Override
    public ResponseEntity<?> delete(
            @PathVariable("code") UUID uuid) {
        return service.delete(uuid);
    }

    @Override
    public ResponseEntity<?> get(UUID uuid) {
        return service.get(uuid);
    }

    @Override
    public ResponseEntity<?> list() {
        return service.getAll();
    }

    @RequestMapping(value = "changeMenu",method = RequestMethod.PATCH)
    public ResponseEntity<?> changeMenu(@RequestBody ChangeMenuDTO dto) {
        return service.changeMenu(dto.getSubmenuId(),dto.getMenuId());
    }

    @RequestMapping("changeOrder")
    public ResponseEntity<Data<List<InfoGroupDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
        return service.changeOrder(baseOrderDTO);
    }
}
