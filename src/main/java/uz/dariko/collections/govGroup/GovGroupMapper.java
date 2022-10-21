package uz.dariko.collections.govGroup;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.dto.SubMenuAdminDTO;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.admin.dto.AdminDTO;
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
        govGroupDTO.setUzDescription(entity.getUzDescription());
        govGroupDTO.setRuDescription(entity.getRuDescription());
        govGroupDTO.setKrDescription(entity.getKrDescription());
        govGroupDTO.setId(entity.getId());
        govGroupDTO.setRank(entity.getRank());
        govGroupDTO.setMenuId(entity.getMenu().getId());
        govGroupDTO.setType("stateEmployee");
        govGroupDTO.setVisible(entity.isVisible());
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


    default GovGroup fromCreateDto(GovGroupCreateDTO createDto){
        GovGroup govGroup = new GovGroup();
        govGroup.setRank(createDto.getRank());
        govGroup.setKrDescription(createDto.getKrDescription());
        govGroup.setUzDescription(createDto.getUzDescription());
        govGroup.setRuDescription(createDto.getRuDescription());
        govGroup.setUzName(createDto.getUzName());
        govGroup.setKrName(createDto.getKrName());
        govGroup.setRuName(createDto.getRuName());
        govGroup.setVisible(createDto.isVisible());
        return govGroup;
    }

    default List<SubMenuAdminDTO> toAdminDto(List<GovGroup> list) {
        List<SubMenuAdminDTO> res = new ArrayList<>();
        for(GovGroup govGroup : list) {
            res.add(toAdminDto(govGroup));
        }
        return res;
    }

    default SubMenuAdminDTO toAdminDto(GovGroup govGroup) {
        SubMenuAdminDTO subMenuAdminDTO = new SubMenuAdminDTO();
        subMenuAdminDTO.setId(govGroup.getId());
        subMenuAdminDTO.setKrDescription(govGroup.getKrDescription());
        subMenuAdminDTO.setRuDescription(govGroup.getRuDescription());
        subMenuAdminDTO.setUzDescription(govGroup.getUzDescription());
        subMenuAdminDTO.setRank(govGroup.getRank());
        subMenuAdminDTO.setKrName(govGroup.getKrName());
        subMenuAdminDTO.setUzName(govGroup.getUzName());
        subMenuAdminDTO.setRuName(govGroup.getRuName());
        subMenuAdminDTO.setMenuId(govGroup.getMenu().getId());
        subMenuAdminDTO.setType("stateEmployee");
        subMenuAdminDTO.setVisible(govGroup.isVisible());
        return subMenuAdminDTO;
    }
}
