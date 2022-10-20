package uz.dariko.collections.govGroup;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.govGroup.dto.GovGroupCreateDTO;
import uz.dariko.collections.govGroup.dto.GovGroupDTO;
import uz.dariko.collections.govGroup.dto.GovGroupUpdateDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface GovGroupMapper extends AbstractMapper<GovGroupCreateDTO, GovGroupUpdateDTO, GovGroupDTO, GovGroup> {

    @Override
    default GovGroupDTO toDto(GovGroup entity){
        GovGroupDTO govGroupDTO = new GovGroupDTO();
        govGroupDTO.setUzName(entity.getUzName());
        govGroupDTO.setRuName(entity.getRuName());
        govGroupDTO.setKrName(entity.getKrName());
        govGroupDTO.setDescription(entity.getDescription());
        govGroupDTO.setId(entity.getId());
        return govGroupDTO;
    }

    @Override
    default List<GovGroupDTO> toDto(List<GovGroup> entities){
        List<GovGroupDTO> res = new ArrayList<>();
        for(GovGroup g : entities) {
            res.add(toDto(g));
        }
        return res;
    }
}
