package uz.dariko.collections.newsSphere;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.newsSphere.dto.NewsSphereCreateDTO;
import uz.dariko.collections.newsSphere.dto.NewsSphereDTO;
import uz.dariko.collections.newsSphere.dto.NewsSphereUpdateDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface NewsSphereMapper extends AbstractMapper<NewsSphereCreateDTO, NewsSphereUpdateDTO, NewsSphereDTO,NewsSphere> {


    @Override
    default NewsSphere fromCreateDto(NewsSphereCreateDTO createDto){
        NewsSphere newsSphere = new NewsSphere();
        newsSphere.setKrName(createDto.getKrName());
        newsSphere.setUzName(createDto.getUzName());
        newsSphere.setRuName(createDto.getRuName());
        return newsSphere;
    }

    @Override
    default NewsSphereDTO toDto(NewsSphere entity){
        NewsSphereDTO dto = new NewsSphereDTO();
        dto.setId(entity.getId());
        dto.setRuName(entity.getRuName());
        dto.setKrName(entity.getKrName());
        dto.setUzName(entity.getUzName());
        return dto;
    }

    default List<NewsSphereDTO> toDto(List<NewsSphere> list) {
        List<NewsSphereDTO> res = new ArrayList<>();
        for(NewsSphere n : list) {
            res.add(toDto(n));
        }
        return res;
    }

}
