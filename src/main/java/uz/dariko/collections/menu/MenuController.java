package uz.dariko.collections.menu;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.base.dto.BaseOrderDTO;
import uz.dariko.collections.menu.dto.MenuCreateDTO;
import uz.dariko.collections.menu.dto.MenuDTO;
import uz.dariko.collections.menu.dto.MenuUpdateDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeDTO;
import uz.dariko.response.Data;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("menu")
public class MenuController extends AbstractController<MenuService> implements GenericCRUDController<MenuCreateDTO, MenuUpdateDTO, MenuDTO, UUID> {

    public MenuController(MenuService service) {
        super(service);
    }

    @Override
    public ResponseEntity<?> create(MenuCreateDTO DTO) throws Exception {
        return service.create(DTO);
    }

    @Override
    public ResponseEntity<?> update(MenuUpdateDTO DTO) {
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
        return service.get();
    }

    @GetMapping("/getForAdmin")
    public ResponseEntity<?> getForAdmin(){
        return service.getForAdmin();
    }

    @RequestMapping("changeOrder")
    public ResponseEntity<Data<List<MenuDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
        return service.changeOrder(baseOrderDTO);
    }
}
