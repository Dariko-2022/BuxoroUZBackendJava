package uz.dariko.collections.stateEmloyee;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

//    @RequestMapping("changeOrder")
//    public ResponseEntity<Data<List<StateEmployeeDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
//        return service.changeOrder(baseOrderDTO);
//    }
}
