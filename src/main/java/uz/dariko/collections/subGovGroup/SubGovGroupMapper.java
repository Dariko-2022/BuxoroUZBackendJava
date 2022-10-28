package uz.dariko.collections.subGovGroup;

import org.mapstruct.Mapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.dariko.base.dto.SubMenuAdminDTO;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.subGovGroup.dto.SubGovGroupCreateDTO;
import uz.dariko.collections.subGovGroup.dto.SubGovGroupDTO;
import uz.dariko.collections.subGovGroup.dto.SubGovGroupUpdateDTO;
import uz.dariko.base.mapper.AbstractMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface SubGovGroupMapper extends AbstractMapper<SubGovGroupCreateDTO, SubGovGroupUpdateDTO, SubGovGroupDTO, SubGovGroup> {

    @Override
    default SubGovGroupDTO toDto(SubGovGroup entity){
        SubGovGroupDTO govGroupDTO = new SubGovGroupDTO();
        govGroupDTO.setUzName(entity.getUzName());
        govGroupDTO.setRuName(entity.getRuName());
        govGroupDTO.setKrName(entity.getKrName());
        govGroupDTO.setUzDescription(entity.getUzDescription());
        govGroupDTO.setRuDescription(entity.getRuDescription());
        govGroupDTO.setKrDescription(entity.getKrDescription());
        govGroupDTO.setId(entity.getId());
        govGroupDTO.setRank(entity.getRank());
        govGroupDTO.setSubmenuId(entity.getSubmenu().getId());
        govGroupDTO.setType("stateEmployee");
        return govGroupDTO;
    }

    @Override
    default List<SubGovGroupDTO> toDto(List<SubGovGroup> entities){
        List<SubGovGroupDTO> res = new ArrayList<>();
        for(SubGovGroup g : entities) {
            res.add(toDto(g));
        }
        return res;
    }


    default SubGovGroup fromCreateDto(SubGovGroupCreateDTO createDto){
        SubGovGroup govGroup = new SubGovGroup();
        govGroup.setRank(createDto.getRank());
        govGroup.setKrDescription(createDto.getKrDescription());
        govGroup.setUzDescription(createDto.getUzDescription());
        govGroup.setRuDescription(createDto.getRuDescription());
        govGroup.setUzName(createDto.getUzName());
        govGroup.setKrName(createDto.getKrName());
        govGroup.setRuName(createDto.getRuName());
        //
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        govGroup.setUpdatedBy(sessionUser.getId());
        govGroup.setUpdatedAt(LocalDateTime.now());
        //
        return govGroup;
    }


}
