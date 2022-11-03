package uz.dariko.collections.stateEmloyee;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.base.dto.BaseOrderDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeCreateDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeUpdateDTO;
import uz.dariko.response.Data;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("stateEmployee/")
public class StateEmployeeController extends AbstractController<StateEmployeeService> implements GenericCRUDController<StateEmployeeCreateDTO, StateEmployeeUpdateDTO, StateEmployeeDTO, UUID> {
    public StateEmployeeController(StateEmployeeService service) {
        super(service);
    }

    @Override
    public ResponseEntity<?> create(StateEmployeeCreateDTO DTO) throws Exception {
        return service.create(DTO);
    }

    @PostMapping("createForEmployeeGroup")
    public ResponseEntity<?> createForEmployeeGroup(
            @RequestBody StateEmployeeCreateDTO DTO) throws Exception {
        return service.createForEmployeeGroup(DTO);
    }

    @Override
    public ResponseEntity<?> update(StateEmployeeUpdateDTO DTO) {
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

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(
            @RequestParam(name = "size") int size,
            @RequestParam(name = "page") int page
    ) {
        return  service.getAll(PageRequest.of(page,size));
    }


    @GetMapping("/getAllSubGovGroup")
    public ResponseEntity<?> getAllBySubGovGroup(
            @RequestParam(name = "submenu") UUID submenuID
    ) {
        return  service.getAllBySubmenu(submenuID);
    }









//    @RequestMapping("changeOrder")
//    public ResponseEntity<Data<List<StateEmployeeDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
//        return service.changeOrder(baseOrderDTO);
//    }
}
