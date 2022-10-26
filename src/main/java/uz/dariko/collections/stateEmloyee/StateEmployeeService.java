package uz.dariko.collections.stateEmloyee;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.dariko.base.dto.BaseOrderDTO;
import uz.dariko.base.dto.OrderDTO;
import uz.dariko.base.service.AbstractService;
import uz.dariko.base.service.GenericCRUDService;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeCreateDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeUpdateDTO;
import uz.dariko.response.Data;
import uz.dariko.utils.BaseUtils;
import uz.dariko.utils.EntityGetter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class StateEmployeeService extends AbstractService<StateEmployeeRepository,StateEmployeeValidator> implements GenericCRUDService<StateEmployeeCreateDTO, StateEmployeeUpdateDTO, StateEmployeeDTO, UUID> {
    public StateEmployeeService(StateEmployeeRepository repository, StateEmployeeValidator validator, EntityGetter entityGetter, BaseUtils baseUtils, StateEmployeeMapper mapper) {
        super(repository, validator);
        this.entityGetter = entityGetter;
        this.baseUtils = baseUtils;
        this.mapper = mapper;
    }
    private final EntityGetter entityGetter;

    private final BaseUtils baseUtils;

    private final StateEmployeeMapper mapper;


    @Override
    public ResponseEntity<?> create(StateEmployeeCreateDTO DTO) {
        validator.validOnCreate(DTO);

        StateEmployee stateEmployee = mapper.fromCreateDTO(DTO);
        repository.save(stateEmployee);
        return ResponseEntity.ok("Successfully Created StateEmployee");
    }

    @Override
    public ResponseEntity<?> update(StateEmployeeUpdateDTO DTO) {
        StateEmployee stateEmployee = mapper.fromUpdateDTO(DTO);
        repository.save(stateEmployee);
        return ResponseEntity.ok("Successfully Updated StateEmployee");
    }

    @Override
    public ResponseEntity<?> delete(UUID key) {
        StateEmployee stateEmployee = entityGetter.getStateEmployee(key);
        //
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        stateEmployee.setDeletedBy(sessionUser.getId());
        stateEmployee.setDeletedAt(LocalDateTime.now());
        //
        stateEmployee.setDeleted(true);
        repository.save(stateEmployee);
        return ResponseEntity.ok("Successfully Deleted StateEmployee");
    }

    @Override
    public ResponseEntity<?> get(UUID key) {
        StateEmployee stateEmployee = entityGetter.getStateEmployee(key);
        return ResponseEntity.ok(mapper.toDTO(stateEmployee));
    }

    @Override
    public ResponseEntity<?> list() {
        List<StateEmployee> allByDeleted = repository.findAllByDeleted(false);
        return ResponseEntity.ok(mapper.toDTO(allByDeleted));
    }

//    public ResponseEntity<Data<List<StateEmployeeDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
//        List<OrderDTO> orderDTOS = baseOrderDTO.getOrders();
//        validator.validOnBaseOrderDTO(orderDTOS);
//        List<StateEmployee> entities = new ArrayList<>();
//        for (OrderDTO order : orderDTOS) {
//            StateEmployee entity = repository.setOrOrderNumber(order.getId(), order.getOrder());
//            entities.add(entity);
//        }
//        return new ResponseEntity<>(new Data<>(mapper.toDTO(entities)), HttpStatus.OK);
//    }
}
