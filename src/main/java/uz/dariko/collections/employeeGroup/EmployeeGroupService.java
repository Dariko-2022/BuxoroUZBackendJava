package uz.dariko.collections.employeeGroup;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupCreateDTO;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupDTO;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupUpdateDTO;
import uz.dariko.collections.stateEmloyee.StateEmployee;
import uz.dariko.collections.stateEmloyee.StateEmployeeMapper;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeDTO;
import uz.dariko.utils.BaseUtils;
import uz.dariko.utils.EntityGetter;

import java.util.List;
import java.util.UUID;


@Service
public class EmployeeGroupService implements BaseService {

    private final EntityGetter entityGetter;
    private final EmployeeGroupRepository employeeGroupRepository;
    private final EmployeeGroupMapper employeeGroupMapper;

    private final StateEmployeeMapper stateEmployeeMapper;

    private final BaseUtils baseUtils;



    public EmployeeGroupService(EntityGetter entityGetter, EmployeeGroupRepository employeeGroupRepository, EmployeeGroupMapper employeeGroupMapper, StateEmployeeMapper stateEmployeeMapper, BaseUtils baseUtils) {
        this.entityGetter = entityGetter;
        this.employeeGroupRepository = employeeGroupRepository;
        this.employeeGroupMapper = employeeGroupMapper;
        this.stateEmployeeMapper = stateEmployeeMapper;
        this.baseUtils = baseUtils;
    }

    public ResponseEntity<?> create(EmployeeGroupCreateDTO dto) {
        EmployeeGroup employeeGroup = employeeGroupMapper.fromCreateDto(dto);
        employeeGroup.setSubmenu(entityGetter.getSubmenu(dto.getSubmenuId()));
        EmployeeGroup save = employeeGroupRepository.save(employeeGroup);
        EmployeeGroupDTO employeeGroupDTO = employeeGroupMapper.toDto(save);
        return ResponseEntity.ok(employeeGroupDTO);
    }

    public EmployeeGroup createForSubmenu(EmployeeGroupCreateDTO dto) {
        EmployeeGroup employeeGroup = employeeGroupMapper.fromCreateDto(dto);
        employeeGroup.setSubmenu(entityGetter.getSubmenu(dto.getSubmenuId()));
        return employeeGroupRepository.save(employeeGroup);
    }

    public ResponseEntity<?> update(EmployeeGroupUpdateDTO dto) {
        EmployeeGroup employeeGroup = employeeGroupMapper.fromUpdateDto(dto);
        employeeGroup.setSubmenu(entityGetter.getSubmenu(dto.getSubmenuId()));
        EmployeeGroup save = employeeGroupRepository.save(employeeGroup);
        EmployeeGroupDTO employeeGroupDTO = employeeGroupMapper.toDto(save);
        return ResponseEntity.ok(employeeGroupDTO);
    }

    public void addEmployee(UUID submenuID,StateEmployee stateEmployee) {
        EmployeeGroup employeeGroup = entityGetter.getEmployeeGroupBtSubmenuId(submenuID);
        List<StateEmployee> employeeList = employeeGroup.getEmployeeList();
        employeeList.add(stateEmployee);
        employeeGroup.setEmployeeList(employeeList);
        employeeGroupRepository.save(employeeGroup);

    }

    public ResponseEntity<?> getBySubmenuId(String Id) {
        UUID submenuId = baseUtils.parseUUID(Id);
        EmployeeGroup employeeGroupBtSubmenuId = entityGetter.getEmployeeGroupBtSubmenuId(submenuId);
        EmployeeGroupDTO employeeGroupDTO = employeeGroupMapper.toDto(employeeGroupBtSubmenuId);
        List<StateEmployeeDTO> stateEmployeeDTOS = stateEmployeeMapper.toDTO(employeeGroupBtSubmenuId.getEmployeeList());
        employeeGroupDTO.setEmployeeDTOS(stateEmployeeDTOS);
        return ResponseEntity.ok(employeeGroupDTO);
    }


}
