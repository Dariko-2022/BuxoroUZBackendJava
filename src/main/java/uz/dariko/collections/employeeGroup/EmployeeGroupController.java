package uz.dariko.collections.employeeGroup;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupCreateDTO;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupDTO;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupUpdateDTO;

import java.util.UUID;

@RestController
@RequestMapping("employeeGroup")
public class EmployeeGroupController extends AbstractController<EmployeeGroupService> implements GenericCRUDController<EmployeeGroupCreateDTO, EmployeeGroupUpdateDTO, EmployeeGroupDTO, UUID> {

    public EmployeeGroupController(EmployeeGroupService service) {
        super(service);
    }

    @Override
    public ResponseEntity<?> create(EmployeeGroupCreateDTO DTO) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> update(EmployeeGroupUpdateDTO DTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(@PathVariable(name = "code") UUID code) {
        return null;
    }

    @Override
    public ResponseEntity<?> get(@PathVariable(name = "code") UUID code) {
        return null;
    }

    @Override
    public ResponseEntity<?> list() {
        return null;
    }
}
