package uz.dariko.collections.stateEmloyee;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.BaseMapper;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeDTO;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring")
public interface StateEmployeeMapper extends BaseMapper {
    default StateEmployeeDTO toStateEmployeeDTO(StateEmployee stateEmployee){
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
