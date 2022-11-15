package uz.dariko.collections.stateEmloyee;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.base.dto.OrderDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeCreateDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeUpdateDTO;

@RestController
@RequestMapping("stateEmployee")
public class StateEmployeeController extends AbstractController<StateEmployeeService> implements GenericCRUDController<StateEmployeeCreateDTO, StateEmployeeUpdateDTO, StateEmployeeDTO, String> {
    public StateEmployeeController(StateEmployeeService service) {
        super(service);
    }

    @Override
    public ResponseEntity<?> create(StateEmployeeCreateDTO DTO) throws Exception {
        return service.createForSubGovGroup(DTO);
    }

    @PostMapping("/createHokim")
    public ResponseEntity<?> createHokim(@RequestBody StateEmployeeCreateDTO dto) {
        return service.createHokim(dto);
    }

    @PostMapping("/createForEmployeeGroup")
    public ResponseEntity<?> createForEmployeeGroup( @RequestBody StateEmployeeCreateDTO DTO) throws Exception {
        return service.createForEmployeeGroup(DTO);
    }

    @PostMapping("/createForSector")
    public ResponseEntity<?> createForSector(@RequestBody StateEmployeeCreateDTO DTO) throws Exception {
        return service.createForSector(DTO);
    }

    @Override
    public ResponseEntity<?> update(StateEmployeeUpdateDTO DTO) {
        return service.update(DTO);
    }

    @Override
    public ResponseEntity<?> delete(String code) {
        return service.delete(code);
    }

    @Override
    public ResponseEntity<?> get(String code) {
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
            @RequestParam(name = "submenu") String submenuID
    ) {
        return  service.getAllBySubGovGroup(submenuID);
    }

    @PostMapping("/editOrderForSubGovGroup")
    public ResponseEntity<?> editOrderForSubGovGroup(@RequestBody OrderDTO orderDTO) {
        return service.editOrderForSubGovGroup(orderDTO);
    }

    @GetMapping("/getHokim")
    public ResponseEntity<?> getHokim() {
        return service.getHokim();
    }










//    @RequestMapping("changeOrder")
//    public ResponseEntity<Data<List<StateEmployeeDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
//        return service.changeOrder(baseOrderDTO);
//    }
}
