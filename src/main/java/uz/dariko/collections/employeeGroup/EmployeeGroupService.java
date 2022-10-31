package uz.dariko.collections.employeeGroup;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupCreateDTO;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupDTO;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupUpdateDTO;
import uz.dariko.collections.stateEmloyee.StateEmployee;
import uz.dariko.collections.subGovGroup.SubGovGroup;
import uz.dariko.utils.EntityGetter;

import java.util.List;
import java.util.UUID;


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

//    public ResponseEntity<?> create(EmployeeGroupCreateDTO dto) {
//        EmployeeGroup employeeGroup = employeeGroupMapper.fromCreateDto(dto);
//        employeeGroup.setEmployeeList(govGroupOrderService.create(dto.getOrderList()));
//        employeeGroup.setSubmenu(entityGetter.getSubmenu(dto.getSubmenuId()));
//        EmployeeGroup save = employeeGroupRepository.save(employeeGroup);
//        EmployeeGroupDTO employeeGroupDTO = employeeGroupMapper.toDto(save);
//        return ResponseEntity.ok(employeeGroupDTO);
//    }

//    public ResponseEntity<?> update(EmployeeGroupUpdateDTO dto) {
//        EmployeeGroup employeeGroup = employeeGroupMapper.fromUpdateDto(dto);
//        employeeGroup.setSubmenu(entityGetter.getSubmenu(dto.getSubmenuId()));
//        employeeGroup.setEmployeeList(govGroupOrderService.update(dto.getOrderList()));
//        EmployeeGroup save = employeeGroupRepository.save(employeeGroup);
//        EmployeeGroupDTO employeeGroupDTO = employeeGroupMapper.toDto(save);
//        return ResponseEntity.ok(employeeGroupDTO);
//    }

    public void addEmployee(UUID employeeGroupId,StateEmployee stateEmployee) {
        EmployeeGroup employeeGroup = entityGetter.getEmployeeGroup(employeeGroupId);
        List<StateEmployee> employeeList = employeeGroup.getEmployeeList();
        employeeList.add(stateEmployee);
        employeeGroup.setEmployeeList(employeeList);
        employeeGroupRepository.save(employeeGroup);

    }


}
