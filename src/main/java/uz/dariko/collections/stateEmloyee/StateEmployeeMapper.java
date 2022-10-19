package uz.dariko.collections.stateEmloyee;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.BaseMapper;
import uz.dariko.collections.govGroup.GovGroup;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface StateEmployeeMapper extends BaseMapper {
    default StateEmployeeDTO toStateEmployeeDTO(StateEmployee stateEmployee){
        List<GovGroup> govGroups = stateEmployee.getGovGroups();
        List<UUID> govGroupIDs=new ArrayList<>();
        for (GovGroup govGroup : govGroups) {
            govGroupIDs.add(govGroup.getId());
        }
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
                govGroupIDs,
                stateEmployee.getResponsibility(),
                stateEmployee.getLabor_activity());
    }

    default List<StateEmployeeDTO> toStateEmployeeDTO(List<StateEmployee> stateEmployees){
        List<StateEmployeeDTO> stateEmployeeDTOS=new ArrayList<>();
        for (StateEmployee stateEmployee : stateEmployees) {
            stateEmployeeDTOS.add(toStateEmployeeDTO(stateEmployee));
        }
        return stateEmployeeDTOS;
    }
}
