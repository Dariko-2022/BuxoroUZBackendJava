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


    public List<StateEmployeeDTO> toDTO(List<StateEmployee> stateEmployees){
        List<StateEmployeeDTO> stateEmployeeDTOS=new ArrayList<>();
        for (StateEmployee stateEmployee : stateEmployees) {
            if(!stateEmployee.isDeleted()) {
                stateEmployeeDTOS.add(toDTO(stateEmployee));
            }
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
        stateEmployeeDTO.setUzBirthPlace(stateEmployee.getUzBirthPlace());
        stateEmployeeDTO.setKrBirthPlace(stateEmployee.getKrBirthPlace());
        stateEmployeeDTO.setRuBirthPlace(stateEmployee.getRuBirthPlace());
        stateEmployeeDTO.setNation(stateEmployee.getNation());
        stateEmployeeDTO.setDegree(stateEmployee.getDegree());
        stateEmployeeDTO.setPhoneNumber(stateEmployee.getPhoneNumber());
        stateEmployeeDTO.setUzAdditionalInformation(stateEmployee.getUzAdditionalInformation());
        stateEmployeeDTO.setRuAdditionalInformation(stateEmployee.getRuAdditionalInformation());
        stateEmployeeDTO.setKrAdditionalInformation(stateEmployee.getKrAdditionalInformation());
        stateEmployeeDTO.setImageID(stateEmployee.getImage().getId());
        stateEmployeeDTO.setUzResponsibility(stateEmployee.getUzResponsibility());
        stateEmployeeDTO.setRuResponsibility(stateEmployee.getRuResponsibility());
        stateEmployeeDTO.setKrResponsibility(stateEmployee.getKrResponsibility());
        stateEmployeeDTO.setFacebook(stateEmployee.getFacebook());
        stateEmployeeDTO.setEmail(stateEmployee.getEmail());
        stateEmployeeDTO.setOrderNumber(stateEmployee.getOrderNumber());
        stateEmployeeDTO.setIsHokim(stateEmployee.getIsHokim());
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
        stateEmployee.setUzResponsibility(DTO.getUzResponsibility());
        stateEmployee.setKrResponsibility(DTO.getKrResponsibility());
        stateEmployee.setRuResponsibility(DTO.getRuResponsibility());
        stateEmployee.setFacebook(DTO.getFacebook());
        stateEmployee.setEmail(DTO.getEmail());
        stateEmployee.setUzAdditionalInformation(DTO.getUzAdditionalInformation());
        stateEmployee.setRuAdditionalInformation(DTO.getRuAdditionalInformation());
        stateEmployee.setKrAdditionalInformation(DTO.getKrAdditionalInformation());
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
        stateEmployee.setUzResponsibility(DTO.getUzResponsibility());
        stateEmployee.setKrResponsibility(DTO.getKrResponsibility());
        stateEmployee.setRuResponsibility(DTO.getRuResponsibility());
        stateEmployee.setFacebook(DTO.getFacebook());
        stateEmployee.setEmail(DTO.getEmail());
        stateEmployee.setUzAdditionalInformation(DTO.getUzAdditionalInformation());
        stateEmployee.setKrAdditionalInformation(DTO.getKrAdditionalInformation());
        stateEmployee.setRuAdditionalInformation(DTO.getRuAdditionalInformation());
        stateEmployee.setOrderNumber(DTO.getOrderNumber());
        //
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        stateEmployee.setUpdatedBy(sessionUser.getId());
        stateEmployee.setUpdatedAt(LocalDateTime.now());
        //
        return stateEmployee;
    }

    StateEmployee toCreateDtoFromUpdate(StateEmployeeUpdateDTO DTO) {
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
        stateEmployee.setUzResponsibility(DTO.getUzResponsibility());
        stateEmployee.setKrResponsibility(DTO.getKrResponsibility());
        stateEmployee.setRuResponsibility(DTO.getRuResponsibility());
        stateEmployee.setFacebook(DTO.getFacebook());
        stateEmployee.setEmail(DTO.getEmail());
        stateEmployee.setUzAdditionalInformation(DTO.getUzAdditionalInformation());
        stateEmployee.setRuAdditionalInformation(DTO.getRuAdditionalInformation());
        stateEmployee.setKrAdditionalInformation(DTO.getKrAdditionalInformation());
        //
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        stateEmployee.setCreatedBy(sessionUser.getId());
        stateEmployee.setCreatedAt(LocalDateTime.now());
        //
        return stateEmployee;
    }

    StateEmployee fromHokimCreateDTO(StateEmployee stateEmployee, StateEmployeeCreateDTO DTO) {
        File image = entityGetter.getFile(DTO.getImageID());
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
        stateEmployee.setUzResponsibility(DTO.getUzResponsibility());
        stateEmployee.setKrResponsibility(DTO.getKrResponsibility());
        stateEmployee.setRuResponsibility(DTO.getRuResponsibility());
        stateEmployee.setFacebook(DTO.getFacebook());
        stateEmployee.setEmail(DTO.getEmail());
        stateEmployee.setUzAdditionalInformation(DTO.getUzAdditionalInformation());
        stateEmployee.setRuAdditionalInformation(DTO.getRuAdditionalInformation());
        stateEmployee.setKrAdditionalInformation(DTO.getKrAdditionalInformation());
        stateEmployee.setIsHokim(true);
        //
//        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        stateEmployee.setCreatedBy(sessionUser.getId());
//        stateEmployee.setCreatedAt(LocalDateTime.now());
        //
        return stateEmployee;
    }


}
