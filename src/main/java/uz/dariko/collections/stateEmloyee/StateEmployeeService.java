package uz.dariko.collections.stateEmloyee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.AbstractService;
import uz.dariko.base.service.GenericCRUDService;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.news.dto.NewsDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeCreateDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeUpdateDTO;
import uz.dariko.criteria.ResponsePage;
import uz.dariko.utils.BaseUtils;
import uz.dariko.utils.EntityGetter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class StateEmployeeService extends AbstractService<StateEmployeeRepository,StateEmployeeValidator> implements GenericCRUDService<StateEmployeeCreateDTO, StateEmployeeUpdateDTO, StateEmployeeDTO, UUID> {

    private final EntityGetter entityGetter;
    private final StateEmployeeMapper mapper;
    private final BaseUtils baseUtils;

    public StateEmployeeService(StateEmployeeRepository repository, StateEmployeeValidator validator, EntityGetter entityGetter, StateEmployeeMapper mapper, BaseUtils baseUtils) {
        super(repository, validator);
        this.entityGetter = entityGetter;
        this.mapper = mapper;
        this.baseUtils = baseUtils;
    }


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

    public ResponseEntity<?> getAll(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int pageNumber = pageable.getPageNumber();
        int offset = pageSize * pageNumber;
        Page<StateEmployee> page = repository.findByIsDeleted(false,pageable);
        List<StateEmployee> employees = repository.findAllByDeleted(false, pageSize, offset);
        List<StateEmployeeDTO> stateEmployeeDTOS = mapper.toDTO(employees);
        ResponsePage<StateEmployeeDTO> responsePage = baseUtils.toResponsePage(page, stateEmployeeDTOS);
        return ResponseEntity.ok(responsePage);
    }


}
