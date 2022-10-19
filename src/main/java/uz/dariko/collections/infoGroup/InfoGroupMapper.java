package uz.dariko.collections.infoGroup;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.infoGroup.dto.InfoGroupCreateDTO;
import uz.dariko.collections.infoGroup.dto.InfoGroupDTO;
import uz.dariko.collections.infoGroup.dto.InfoGroupUpdateDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface InfoGroupMapper extends AbstractMapper<InfoGroupCreateDTO, InfoGroupUpdateDTO, InfoGroupDTO, InfoGroup> {
    @Override
    default InfoGroupDTO toDto(InfoGroup entity) {
        InfoGroupDTO infoGroupDTO = new InfoGroupDTO(entity.getUzName(), entity.getKrName(), entity.getRuName());
        infoGroupDTO.setId(entity.getId());
        return infoGroupDTO;

    }

    default List<InfoGroupDTO> toDtoList(List<InfoGroup> entities){
        List<InfoGroupDTO> res = new ArrayList<>();
        for (InfoGroup infoGroup : entities) {
            res.add(toDto(infoGroup));
        }
        return res;
    }

    @Override
    default InfoGroup fromCreateDto(InfoGroupCreateDTO createDto) {
        InfoGroup infoGroup = new InfoGroup(
                createDto.getUzName(),
                createDto.getKrName(),
                createDto.getRuName()
        );
        return infoGroup;
    }


}
