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


    public StateEmployeeMapper(EntityGetter entityGetter) {
        this.entityGetter = entityGetter;
    }

//    public List<StateEmployeeDTO> toDTO(List<StateEmployee> list) {
//        List<StateEmployeeDTO> res = new ArrayList<>();
//        for(StateEmployee s : list) {
//            res.add(toDTO(s));
//        }
//        return res;
//    }

    public List<StateEmployeeDTO> toDTO(List<StateEmployee> stateEmployees){
        List<StateEmployeeDTO> stateEmployeeDTOS=new ArrayList<>();
        for (StateEmployee stateEmployee : stateEmployees) {
            stateEmployeeDTOS.add(toDTO(stateEmployee));
        }
        return stateEmployeeDTOS;
    }

    public StateEmployeeDTO toDTO(StateEmployee stateEmployee){
        StateEmployeeDTO stateEmployeeDTO =  new StateEmployeeDTO();
        stateEmployeeDTO.setId(stateEmployee.getId());
        stateEmployeeDTO.setFirstName(stateEmployee.getFirstName());
        stateEmployeeDTO.setLastName(stateEmployee.getLastName());
        stateEmployeeDTO.setPatronymic(stateEmployee.getPatronymic());
        stateEmployeeDTO.setUzPosition(stateEmployee.getUzPosition());
        stateEmployeeDTO.setRuPosition(stateEmployee.getRuPosition());
        stateEmployeeDTO.setKrPosition(stateEmployee.getKrPosition());
        stateEmployeeDTO.setBirthDate(stateEmployee.getBirthDate());
        stateEmployeeDTO.setKrBirthPlace(stateEmployee.getUzBirthPlace());
        stateEmployeeDTO.setKrBirthPlace(stateEmployee.getKrBirthPlace());
        stateEmployeeDTO.setRuBirthPlace(stateEmployee.getRuBirthPlace());
        stateEmployeeDTO.setNation(stateEmployee.getNation());
        stateEmployeeDTO.setDegree(stateEmployee.getDegree());
        stateEmployeeDTO.setPhoneNumber(stateEmployee.getPhoneNumber());
        stateEmployeeDTO.setAdditionalInformationUz(stateEmployee.getAdditionalInformationUz());
        stateEmployeeDTO.setAdditionalInformationRu(stateEmployee.getAdditionalInformationRu());
        stateEmployeeDTO.setAdditionalInformationKr(stateEmployee.getAdditionalInformationKr());
        stateEmployeeDTO.setImageID(stateEmployee.getImage().getId());
        stateEmployeeDTO.setResponsibility(stateEmployee.getResponsibility());
        stateEmployeeDTO.setLabor_activity(stateEmployee.getLabor_activity());
        stateEmployeeDTO.setOrderNumber(stateEmployee.getOrderNumber());
        return stateEmployeeDTO;
    }

    StateEmployee fromCreateDTO(StateEmployeeCreateDTO DTO){
        File image = entityGetter.getFile(DTO.getImageID());
        StateEmployee stateEmployee = new StateEmployee();
        stateEmployee.setFirstName(DTO.getFirstName());
        stateEmployee.setLastName(DTO.getLastName());
        stateEmployee.setPatronymic(DTO.getPatronymic());
        stateEmployee.setUzPosition(DTO.getUzPosition());
        stateEmployee.setRuPosition(DTO.getRuPosition());
        stateEmployee.setKrPosition(DTO.getKrPosition());
        stateEmployee.setBirthDate(DTO.getBirthDate());
        stateEmployee.setUzBirthPlace(DTO.getUzBirthPlace());
        stateEmployee.setKrBirthPlace(DTO.getKrBirthPlace());
        stateEmployee.setRuBirthPlace(DTO.getRuBirthPlace());
        stateEmployee.setNation(DTO.getNation());
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
        File file = entityGetter.getFile(DTO.getImageID());
        StateEmployee stateEmployee = entityGetter.getStateEmployee(DTO.getId());
        stateEmployee.setFirstName(DTO.getFirstName());
        stateEmployee.setLastName(DTO.getLastName());
        stateEmployee.setPatronymic(DTO.getPatronymic());
        stateEmployee.setUzPosition(DTO.getUzPosition());
        stateEmployee.setRuPosition(DTO.getRuPosition());
        stateEmployee.setKrPosition(DTO.getKrPosition());
        stateEmployee.setBirthDate(DTO.getBirthDate());
        stateEmployee.setUzBirthPlace(DTO.getUzBirthPlace());
        stateEmployee.setKrBirthPlace(DTO.getKrBirthPlace());
        stateEmployee.setRuBirthPlace(DTO.getRuBirthPlace());
        stateEmployee.setNation(DTO.getNation());
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


}
