package uz.dariko.collections.infoGroup;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.dto.SubMenuAdminDTO;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.govGroup.GovGroup;
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
        InfoGroupDTO infoGroupDTO = new InfoGroupDTO(entity.getUzName(), entity.getKrName(), entity.getRuName(),"information",entity.getMenu().getId(),entity.getRank(),entity.isVisible());
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
        InfoGroup infoGroup = new InfoGroup();
        infoGroup.setUzName(createDto.getUzName());
        infoGroup.setKrName(createDto.getKrName());
        infoGroup.setRuName(createDto.getRuName());
        infoGroup.setVisible(true);
        return infoGroup;
    }

    default List<SubMenuAdminDTO> toAdminDto(List<InfoGroup> list) {
        List<SubMenuAdminDTO> res = new ArrayList<>();
        for(InfoGroup infoGroup : list) {
            res.add(toAdminDto(infoGroup));
        }
        return res;
    }

    default SubMenuAdminDTO toAdminDto(InfoGroup infoGroup) {
        SubMenuAdminDTO subMenuAdminDTO = new SubMenuAdminDTO();
        subMenuAdminDTO.setId(infoGroup.getId());
        subMenuAdminDTO.setRank(infoGroup.getRank());
        subMenuAdminDTO.setKrName(infoGroup.getKrName());
        subMenuAdminDTO.setUzName(infoGroup.getUzName());
        subMenuAdminDTO.setRuName(infoGroup.getRuName());
        subMenuAdminDTO.setMenuId(infoGroup.getMenu().getId());
        subMenuAdminDTO.setType("information");
        subMenuAdminDTO.setVisible(infoGroup.isVisible());
        return subMenuAdminDTO;
    }


}
