package uz.dariko.collections.employeeGroup;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupCreateDTO;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupDTO;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupUpdateDTO;
import uz.dariko.collections.order.GovGroupOrderService;
import uz.dariko.utils.EntityGetter;



@Service
public class EmployeeGroupService implements BaseService {

    private final EntityGetter entityGetter;
    private final EmployeeGroupRepository employeeGroupRepository;

    private final EmployeeGroupMapper employeeGroupMapper;
    private final GovGroupOrderService govGroupOrderService;



    public EmployeeGroupService(EntityGetter entityGetter, EmployeeGroupRepository employeeGroupRepository, EmployeeGroupMapper employeeGroupMapper, GovGroupOrderService govGroupOrderService) {
        this.entityGetter = entityGetter;
        this.employeeGroupRepository = employeeGroupRepository;
        this.employeeGroupMapper = employeeGroupMapper;
        this.govGroupOrderService = govGroupOrderService;
    }

    public ResponseEntity<?> create(EmployeeGroupCreateDTO dto) {
        EmployeeGroup employeeGroup = employeeGroupMapper.fromCreateDto(dto);
        employeeGroup.setEmployeeList(govGroupOrderService.create(dto.getOrderList()));
        employeeGroup.setSubmenu(entityGetter.getSubmenu(dto.getSubmenuId()));
        EmployeeGroup save = employeeGroupRepository.save(employeeGroup);
        EmployeeGroupDTO employeeGroupDTO = employeeGroupMapper.toDto(save);
        return ResponseEntity.ok(employeeGroupDTO);
    }

    public ResponseEntity<?> update(EmployeeGroupUpdateDTO dto) {
        EmployeeGroup employeeGroup = employeeGroupMapper.fromUpdateDto(dto);
        employeeGroup.setSubmenu(entityGetter.getSubmenu(dto.getSubmenuId()));
        employeeGroup.setEmployeeList(govGroupOrderService.update(dto.getOrderList()));
        EmployeeGroup save = employeeGroupRepository.save(employeeGroup);
        EmployeeGroupDTO employeeGroupDTO = employeeGroupMapper.toDto(save);
        return ResponseEntity.ok(employeeGroupDTO);
    }


}
