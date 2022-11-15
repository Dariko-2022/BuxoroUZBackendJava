package uz.dariko.collections.stateEmloyee;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.dariko.base.dto.OrderDTO;
import uz.dariko.base.service.AbstractService;
import uz.dariko.base.service.GenericCRUDService;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.employeeGroup.EmployeeGroup;
import uz.dariko.collections.employeeGroup.EmployeeGroupService;
import uz.dariko.collections.sector.SectorService;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeCreateDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeUpdateDTO;
import uz.dariko.collections.subGovGroup.SubGovGroup;
import uz.dariko.collections.subGovGroup.SubGovGroupService;
import uz.dariko.criteria.ResponsePage;
import uz.dariko.utils.BaseUtils;
import uz.dariko.utils.EntityGetter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StateEmployeeService extends AbstractService<StateEmployeeRepository,StateEmployeeValidator> implements GenericCRUDService<StateEmployeeCreateDTO, StateEmployeeUpdateDTO, StateEmployeeDTO, String> {

    private final EntityGetter entityGetter;
    private final StateEmployeeMapper mapper;
    private final SubGovGroupService subGovGroupService;
    private final BaseUtils baseUtils;
    private final EmployeeGroupService employeeGroupService;
    private final SectorService sectorService;

    public StateEmployeeService(StateEmployeeRepository repository, StateEmployeeValidator validator, EntityGetter entityGetter, StateEmployeeMapper mapper, SubGovGroupService subGovGroupService, BaseUtils baseUtils, EmployeeGroupService employeeGroupService, SectorService sectorService) {
        super(repository, validator);
        this.entityGetter = entityGetter;
        this.mapper = mapper;
        this.subGovGroupService = subGovGroupService;
        this.baseUtils = baseUtils;
        this.employeeGroupService = employeeGroupService;
        this.sectorService = sectorService;
    }


    @Override
    public ResponseEntity<?> create(StateEmployeeCreateDTO dto) {
        StateEmployee stateEmployee = mapper.fromCreateDTO(dto);
        StateEmployee save = repository.save(stateEmployee);
        StateEmployeeDTO stateEmployeeDTO = mapper.toDTO(save);
        return ResponseEntity.ok(stateEmployeeDTO);
    }

    public ResponseEntity<?> createForSector(StateEmployeeCreateDTO dto) {
        UUID sectorId = dto.getSubmenuID();
        StateEmployee stateEmployee = mapper.fromCreateDTO(dto);

        stateEmployee.setImage(entityGetter.getFile(dto.getImageID()));
        StateEmployee save = repository.save(stateEmployee);

        return sectorService.updateStateEmployee(save,sectorId);
    }

    public ResponseEntity<?> createForSubGovGroup(StateEmployeeCreateDTO DTO) {
        validator.validOnCreate(DTO);
        SubGovGroup subGovGroup = entityGetter.getSubGovGroup(DTO.getSubmenuID());
        StateEmployee stateEmployee = mapper.fromCreateDTO(DTO);
        stateEmployee.setOrderNumber(1+repository.getOrderNumberBySubGovGroup(subGovGroup.getId()));
        StateEmployee save = repository.save(stateEmployee);
        subGovGroupService.addEmployee(DTO.getSubmenuID(),save);
        StateEmployeeDTO stateEmployeeDTO = mapper.toDTO(save);
        return ResponseEntity.ok(stateEmployeeDTO);
    }

    public ResponseEntity<?> createHokim(StateEmployeeCreateDTO dto) {
        Optional<StateEmployee> hokim = repository.findAllHokim();
        StateEmployee stateEmployee0 = hokim.orElseGet(StateEmployee::new);
        StateEmployee stateEmployee = mapper.fromHokimCreateDTO(stateEmployee0,dto);
        StateEmployee save = repository.save(stateEmployee);
        StateEmployeeDTO stateEmployeeDTO = mapper.toDTO(save);
        return ResponseEntity.ok(stateEmployeeDTO);
    }

    public ResponseEntity<?> createForEmployeeGroup(StateEmployeeCreateDTO DTO) {
        validator.validOnCreate(DTO);
        StateEmployee stateEmployee = mapper.fromCreateDTO(DTO);
        EmployeeGroup employeeGroup = entityGetter.getEmployeeGroupBtSubmenuId(DTO.getSubmenuID());
        Integer orderNumberByEmployeeGroup = repository.getOrderNumberByEmployeeGroup(employeeGroup.getId());
        stateEmployee.setOrderNumber(1+orderNumberByEmployeeGroup);
        StateEmployee save = repository.save(stateEmployee);
        employeeGroupService.addEmployee(DTO.getSubmenuID(),save);
        StateEmployeeDTO stateEmployeeDTO = mapper.toDTO(save);
        return ResponseEntity.ok(stateEmployeeDTO);
    }

    @Override
    public ResponseEntity<?> update(StateEmployeeUpdateDTO DTO) {
        StateEmployee stateEmployee = mapper.fromUpdateDTO(DTO);
        StateEmployee save = repository.save(stateEmployee);
        StateEmployeeDTO stateEmployeeDTO = mapper.toDTO(save);
        return ResponseEntity.ok(stateEmployeeDTO);
    }

    @Override
    public ResponseEntity<?> delete(String code) {
        UUID key = baseUtils.parseUUID(code);
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
    public ResponseEntity<?> get(String code) {
        UUID key = baseUtils.parseUUID(code);
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


    public ResponseEntity<?> getAllBySubGovGroup(String code) {
        UUID submenuID = baseUtils.parseUUID(code);
        List<StateEmployee> employees = repository.findAllByDeletedAndSubGovGroup(submenuID);
        List<StateEmployeeDTO> stateEmployeeDTOS = mapper.toDTO(employees);
        return ResponseEntity.ok(stateEmployeeDTOS);
    }

    public ResponseEntity<?> getHokim() {
        Optional<StateEmployee> allHokim = repository.findAllHokim();
        if(allHokim.isEmpty()) {
            return ResponseEntity.ok("empty");
        }
        StateEmployeeDTO stateEmployeeDTO = mapper.toDTO(allHokim.get());
        return ResponseEntity.ok(stateEmployeeDTO);
    }

    public ResponseEntity<?> editOrderForSubGovGroup(OrderDTO orderDTO) {
        Optional<StateEmployee> byIdAndIsDeleted = repository.findByIdAndIsDeleted(orderDTO.getId(), false);
        if(byIdAndIsDeleted.isEmpty()) {
            return ResponseEntity.status(404).body("Employee Not found");
        }
        StateEmployee stateEmployee = byIdAndIsDeleted.get();
        stateEmployee.setOrderNumber(orderDTO.getOrder());
        stateEmployee.setUpdatedAt(LocalDateTime.now());
        repository.save(stateEmployee);
        List<StateEmployee> allByDeletedAndSubGovGroup = repository.findAllByDeletedAndSubGovGroup(orderDTO.getParentId());
        List<StateEmployeeDTO> stateEmployeeDTOS = mapper.toDTO(allByDeletedAndSubGovGroup);
        return ResponseEntity.ok(stateEmployeeDTOS);
    }


}
