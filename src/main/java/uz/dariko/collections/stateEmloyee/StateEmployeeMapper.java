package uz.dariko.collections.stateEmloyee;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.BaseMapper;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.file.File;
import uz.dariko.collections.region.Region;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeCreateDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeUpdateDTO;
import uz.dariko.utils.EntityGetter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class StateEmployeeMapper implements BaseMapper {
    private final EntityGetter entityGetter;

    private final StateEmployeeRepository repository;

    public StateEmployeeMapper(EntityGetter entityGetter, StateEmployeeRepository repository) {
        this.entityGetter = entityGetter;
        this.repository = repository;
    }

    StateEmployeeDTO toDTO(StateEmployee stateEmployee){
        return new StateEmployeeDTO(stateEmployee.getFirstName(),
                stateEmployee.getLastName(),
                stateEmployee.getPatronymic(),
                stateEmployee.getUzPosition(),
                stateEmployee.getRuPosition(),
                stateEmployee.getKrPosition(),
                stateEmployee.getBirthDate(),
                stateEmployee.getBirthPlace(),
                stateEmployee.getNation(),
                stateEmployee.getRegion().getId(),
                stateEmployee.getDegree(),
                stateEmployee.getPhoneNumber(),
                stateEmployee.getAdditionalInformationUz(),
                stateEmployee.getAdditionalInformationRu(),
                stateEmployee.getAdditionalInformationKr(),
                stateEmployee.getImage().getId(),
                stateEmployee.getResponsibility(),
                stateEmployee.getLabor_activity(),
                stateEmployee.getOrderNumber());
    }

    StateEmployee fromCreateDTO(StateEmployeeCreateDTO DTO){
        File image = entityGetter.getFile(DTO.getImageID());
        Region region = entityGetter.getRegion(DTO.getRegionID());
        StateEmployee stateEmployee = new StateEmployee();
        stateEmployee.setFirstName(DTO.getFirstName());
        stateEmployee.setLastName(DTO.getLastName());
        stateEmployee.setPatronymic(DTO.getPatronymic());
        stateEmployee.setUzPosition(DTO.getUzPosition());
        stateEmployee.setRuPosition(DTO.getRuPosition());
        stateEmployee.setKrPosition(DTO.getKrPosition());
        stateEmployee.setBirthDate(DTO.getBirthDate());
        stateEmployee.setBirthPlace(DTO.getBirthPlace());
        stateEmployee.setNation(DTO.getNation());
        stateEmployee.setRegion(region);
        stateEmployee.setDegree(DTO.getDegree());
        stateEmployee.setPhoneNumber(DTO.getPhoneNumber());
        stateEmployee.setImage(image);
        stateEmployee.setResponsibility(DTO.getResponsibility());
        stateEmployee.setLabor_activity(DTO.getLabor_activity());
        stateEmployee.setAdditionalInformationUz(DTO.getAdditionalInformationUz());
        stateEmployee.setAdditionalInformationRu(DTO.getAdditionalInformationRu());
        stateEmployee.setAdditionalInformationKr(DTO.getAdditionalInformationKr());
        //
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        stateEmployee.setCreatedBy(sessionUser.getId());
        stateEmployee.setCreatedAt(LocalDateTime.now());
        //
        return stateEmployee;
    }

    StateEmployee fromUpdateDTO(StateEmployeeUpdateDTO DTO){
        Region region = entityGetter.getRegion(DTO.getRegionID());
        File file = entityGetter.getFile(DTO.getImageID());
        StateEmployee stateEmployee = entityGetter.getStateEmployee(DTO.getId());
        stateEmployee.setFirstName(DTO.getFirstName());
        stateEmployee.setLastName(DTO.getLastName());
        stateEmployee.setPatronymic(DTO.getPatronymic());
        stateEmployee.setUzPosition(DTO.getUzPosition());
        stateEmployee.setRuPosition(DTO.getRuPosition());
        stateEmployee.setKrPosition(DTO.getKrPosition());
        stateEmployee.setBirthDate(DTO.getBirthDate());
        stateEmployee.setBirthPlace(DTO.getBirthPlace());
        stateEmployee.setNation(DTO.getNation());
        stateEmployee.setRegion(region);
        stateEmployee.setDegree(DTO.getDegree());
        stateEmployee.setPhoneNumber(DTO.getPhoneNumber());
        stateEmployee.setImage(file);
        stateEmployee.setResponsibility(DTO.getResponsibility());
        stateEmployee.setLabor_activity(DTO.getLabor_activity());
        stateEmployee.setAdditionalInformationUz(DTO.getAdditionalInformationUz());
        stateEmployee.setAdditionalInformationRu(DTO.getAdditionalInformationRu());
        stateEmployee.setAdditionalInformationKr(DTO.getAdditionalInformationKr());
        //
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        stateEmployee.setUpdatedBy(sessionUser.getId());
        stateEmployee.setUpdatedAt(LocalDateTime.now());
        //
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
