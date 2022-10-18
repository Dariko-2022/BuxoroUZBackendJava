package uz.dariko.collections.stateEmloyee;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.AbstractService;
import uz.dariko.base.service.GenericCRUDService;
import uz.dariko.collections.file.File;
import uz.dariko.collections.govGroup.GovGroup;
import uz.dariko.collections.region.Region;
import uz.dariko.collections.stateEmloyee.dto.StateCreateDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeUpdateDTO;
import uz.dariko.utils.BaseUtils;
import uz.dariko.utils.EntityGetter;

import java.util.List;
import java.util.UUID;

@Service
public class StateEmployeeService extends AbstractService<StateEmployeeRepository,StateEmployeeValidator> implements GenericCRUDService<StateCreateDTO, StateEmployeeUpdateDTO, StateEmployeeDTO, UUID> {
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
    public ResponseEntity<?> create(StateCreateDTO DTO) {
        validator.validOnCreate(DTO);

        File image = entityGetter.getFile(DTO.getImageID());
        Region region = entityGetter.getRegion(DTO.getRegionID());
        List<GovGroup> govGroups = entityGetter.getGovGroup(DTO.getGovGroupIDs());
        StateEmployee stateEmployee=new StateEmployee
                (DTO.getFirstName(), DTO.getLastName(),
                        DTO.getPatronymic(), DTO.getBirthDate(),
                        DTO.getBirthPlace(),
                        DTO.getNation(), region, DTO.getDegree(),
                        DTO.getPhoneNumber(), image,govGroups, DTO.getResponsibility(),DTO.getLabor_activity());
        repository.save(stateEmployee);
        return ResponseEntity.ok("Successfully Created StateEmployee");
    }

    @Override
    public ResponseEntity<?> update(StateEmployeeUpdateDTO DTO) {
        Region region = entityGetter.getRegion(DTO.getRegionID());
        File file = entityGetter.getFile(DTO.getImageID());
        List<GovGroup> govGroups = entityGetter.getGovGroup(DTO.getGovGroupIDs());
        StateEmployee stateEmployee = entityGetter.getStateEmployee(DTO.getId());
        stateEmployee.setFirstName(DTO.getFirstName());
        stateEmployee.setLastName(DTO.getLastName());
        stateEmployee.setPatronymic(DTO.getPatronymic());
        stateEmployee.setBirthDate(DTO.getBirthDate());
        stateEmployee.setBirthPlace(DTO.getBirthPlace());
        stateEmployee.setNation(DTO.getNation());
        stateEmployee.setRegion(region);
        stateEmployee.setDegree(DTO.getDegree());
        stateEmployee.setPhoneNumber(DTO.getPhoneNumber());
        stateEmployee.setImage(file);
        stateEmployee.setGovGroups(govGroups);
        stateEmployee.setResponsibility(DTO.getResponsibility());
        stateEmployee.setLabor_activity(DTO.getLabor_activity());
        repository.save(stateEmployee);
        return ResponseEntity.ok("Successfully Updated StateEmployee");
    }

    @Override
    public ResponseEntity<?> delete(UUID key) {
        StateEmployee stateEmployee = entityGetter.getStateEmployee(key);
        stateEmployee.setDeleted(true);
        repository.save(stateEmployee);
        return ResponseEntity.ok("Successfully Deleted StateEmployee");
    }

    @Override
    public ResponseEntity<?> get(UUID key) {
        StateEmployee stateEmployee = entityGetter.getStateEmployee(key);

        return ResponseEntity.ok(mapper.toStateEmployeeDTO(stateEmployee));
    }

    @Override
    public ResponseEntity<?> list() {
        List<StateEmployee> allByDeleted = repository.findAllByDeleted(false);
        return ResponseEntity.ok(mapper.toStateEmployeeDTO(allByDeleted));
    }
}
