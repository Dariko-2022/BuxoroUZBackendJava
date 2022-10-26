package uz.dariko.collections.employeeGroup;


import org.mapstruct.Mapper;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupCreateDTO;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupDTO;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupUpdateDTO;
import uz.dariko.collections.stateEmloyee.StateEmployee;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface EmployeeGroupMapper extends AbstractMapper<EmployeeGroupCreateDTO, EmployeeGroupUpdateDTO, EmployeeGroupDTO,EmployeeGroup> {

    @Override
    default EmployeeGroupDTO toDto(EmployeeGroup entity){
        EmployeeGroupDTO employeeGroupDTO = new EmployeeGroupDTO();
        employeeGroupDTO.setId(entity.getId());
        employeeGroupDTO.setUzName(employeeGroupDTO.getUzName());
        employeeGroupDTO.setRuName(employeeGroupDTO.getRuName());
        employeeGroupDTO.setKrName(employeeGroupDTO.getKrName());
        List<UUID> list = new ArrayList<>();
        for(StateEmployee e : entity.getStateEmployeeList()) {
            list.add(e.getId());
        }
        employeeGroupDTO.setEmployeeIDs(list);
        return employeeGroupDTO;
    }

    @Override
    default EmployeeGroup fromCreateDto(EmployeeGroupCreateDTO createDto){
        EmployeeGroup employeeGroup = new EmployeeGroup();
        employeeGroup.setUzName(createDto.getUzName());
        employeeGroup.setRuName(createDto.getRuName());
        employeeGroup.setKrName(createDto.getKrName());
        return employeeGroup;
    }

    @Override
    default List<EmployeeGroupDTO> toDto(List<EmployeeGroup> entities){
        List<EmployeeGroupDTO> res = new ArrayList<>();
        for(EmployeeGroup e : entities) {
            res.add(toDto(e));
        }
        return res;
    }

    @Override
    default EmployeeGroup fromUpdateDto(EmployeeGroupUpdateDTO updateDto) {
        EmployeeGroup employeeGroup = new EmployeeGroup();
        employeeGroup.setId(updateDto.getId());
        employeeGroup.setUzName(updateDto.getUzName());
        employeeGroup.setRuName(updateDto.getRuName());
        employeeGroup.setKrName(updateDto.getKrName());
        return employeeGroup;
    }
}
