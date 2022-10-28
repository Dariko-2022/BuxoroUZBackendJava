package uz.dariko.collections.submenu;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.base.dto.BaseOrderDTO;
import uz.dariko.collections.submenu.dto.ChangeMenuDTO;
import uz.dariko.collections.submenu.dto.SubmenuCreateDTO;
import uz.dariko.collections.submenu.dto.SubmenuDTO;
import uz.dariko.collections.submenu.dto.SubmenuUpdateDTO;
import uz.dariko.response.Data;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("submenu")
public class SubmenuController extends AbstractController<SubmenuService> implements GenericCRUDController<SubmenuCreateDTO, SubmenuUpdateDTO, SubmenuDTO, UUID>
{


    public SubmenuController(SubmenuService service) {
        super(service);
    }
    @Override
    public ResponseEntity<?> create(SubmenuCreateDTO submenuCreateDto) {
        return service.create(submenuCreateDto);
    }

    @Override
    public ResponseEntity<?> update(SubmenuUpdateDTO DTO) {
        return service.update(DTO);
    }

    @Override
    public ResponseEntity<?> delete(
            @PathVariable("code") UUID id) {
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
    public ResponseEntity<Data<List<SubmenuDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
        return service.changeOrder(baseOrderDTO);
    }

    @RequestMapping(value = "changeMenu",method = RequestMethod.PATCH)
    public ResponseEntity<?> changeMenu(@RequestBody ChangeMenuDTO dto) {
        return service.changeMenu(dto.getSubmenuId(),dto.getMenuId());
    }


}
