package uz.dariko.collections.stateEmloyee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.BaseMapper;
import uz.dariko.collections.file.File;
import uz.dariko.collections.govGroup.GovGroup;
import uz.dariko.collections.region.Region;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeCreateDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeUpdateDTO;
import uz.dariko.utils.EntityGetter;

import java.util.ArrayList;
import java.util.List;

@Component
public class StateEmployeeMapper implements BaseMapper {
    @Autowired
    EntityGetter entityGetter;
     StateEmployeeDTO toDTO(StateEmployee stateEmployee){
        return new StateEmployeeDTO(stateEmployee.getFirstName(),
                stateEmployee.getLastName(),
                stateEmployee.getPatronymic(),
                stateEmployee.getBirthDate(),
                stateEmployee.getBirthPlace(),
                stateEmployee.getNation(),
                stateEmployee.getRegion().getId(),
                stateEmployee.getDegree(),
                stateEmployee.getPhoneNumber(),
                stateEmployee.getImage().getId(),
                stateEmployee.getGovGroup().getId(),
                stateEmployee.getResponsibility(),
                stateEmployee.getLabor_activity());
    }

    StateEmployee fromCreateDTO(StateEmployeeCreateDTO DTO){
        File image = entityGetter.getFile(DTO.getImageID());
        Region region = entityGetter.getRegion(DTO.getRegionID());
        return new StateEmployee
                (DTO.getFirstName(), DTO.getLastName(),
                        DTO.getPatronymic(), DTO.getBirthDate(),
                        DTO.getBirthPlace(),
                        DTO.getNation(), region, DTO.getDegree(),
                        DTO.getPhoneNumber(), image,entityGetter.getGovGroup(DTO.getGovGroupID()), DTO.getResponsibility(),DTO.getLabor_activity());

    }

    StateEmployee fromUpdateDTO(StateEmployeeUpdateDTO DTO){
        Region region = entityGetter.getRegion(DTO.getRegionID());
        File file = entityGetter.getFile(DTO.getImageID());
        GovGroup govGroups = entityGetter.getGovGroup(DTO.getGovGroupID());
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
        stateEmployee.setGovGroup(govGroups);
        stateEmployee.setResponsibility(DTO.getResponsibility());
        stateEmployee.setLabor_activity(DTO.getLabor_activity());
        return stateEmployee;
    }

     List<StateEmployeeDTO> toDTO(List<StateEmployee> stateEmployees){
        List<StateEmployeeDTO> stateEmployeeDTOS=new ArrayList<>();
        for (StateEmployee stateEmployee : stateEmployees) {
            stateEmployeeDTOS.add(toDTO(stateEmployee));
        }
        return stateEmployeeDTOS;
    }
}
