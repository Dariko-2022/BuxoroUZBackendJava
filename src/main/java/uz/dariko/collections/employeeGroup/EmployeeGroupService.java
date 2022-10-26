package uz.dariko.collections.employeeGroup;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupCreateDTO;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupDTO;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupUpdateDTO;
import uz.dariko.utils.EntityGetter;


@Service
public class EmployeeGroupService implements BaseService {

    private final EntityGetter entityGetter;
    private final EmployeeGroupRepository employeeGroupRepository;

    private final EmployeeGroupMapper employeeGroupMapper;


    public EmployeeGroupService(EntityGetter entityGetter, EmployeeGroupRepository employeeGroupRepository, EmployeeGroupMapper employeeGroupMapper) {
        this.entityGetter = entityGetter;
        this.employeeGroupRepository = employeeGroupRepository;
        this.employeeGroupMapper = employeeGroupMapper;
    }

    public ResponseEntity<?> create(EmployeeGroupCreateDTO dto) {
        EmployeeGroup employeeGroup = employeeGroupMapper.fromCreateDto(dto);
        employeeGroup.setStateEmployeeList(entityGetter.getStateEmployee(dto.getEmployeeList()));
        employeeGroup.setMenu(entityGetter.getMenu(dto.getMenuId()));
        EmployeeGroup save = employeeGroupRepository.save(employeeGroup);
        EmployeeGroupDTO employeeGroupDTO = employeeGroupMapper.toDto(save);
        return ResponseEntity.ok(employeeGroupDTO);
    }

    public ResponseEntity<?> update(EmployeeGroupUpdateDTO dto) {
        EmployeeGroup employeeGroup = employeeGroupMapper.fromUpdateDto(dto);
        employeeGroup.setMenu(entityGetter.getMenu(dto.getMenuId()));
        employeeGroup.setStateEmployeeList(entityGetter.getStateEmployee(dto.getEmployeeIDs()));
        EmployeeGroup save = employeeGroupRepository.save(employeeGroup);
        EmployeeGroupDTO employeeGroupDTO = employeeGroupMapper.toDto(save);
        return ResponseEntity.ok(employeeGroupDTO);
    }


}
