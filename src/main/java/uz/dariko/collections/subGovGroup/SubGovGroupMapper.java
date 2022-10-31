package uz.dariko.collections.subGovGroup;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.BaseMapper;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.stateEmloyee.StateEmployeeMapper;
import uz.dariko.collections.subGovGroup.dto.SubGovGroupCreateDTO;
import uz.dariko.collections.subGovGroup.dto.SubGovGroupDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class SubGovGroupMapper implements BaseMapper {


    private final StateEmployeeMapper stateEmployeeMapper;

    public SubGovGroupMapper( StateEmployeeMapper stateEmployeeMapper) {
        this.stateEmployeeMapper = stateEmployeeMapper;
    }

    public SubGovGroupDTO toDto(SubGovGroup entity) {
        SubGovGroupDTO subGovGroupDTO = new SubGovGroupDTO();
        subGovGroupDTO.setId(entity.getId());
        subGovGroupDTO.setUzName(entity.getUzName());
        subGovGroupDTO.setRuName(entity.getRuName());
        subGovGroupDTO.setKrName(entity.getKrName());
        subGovGroupDTO.setKrDescriptionTitle(entity.getKrDescriptionTitle());
        subGovGroupDTO.setUzDescriptionTitle(entity.getUzDescriptionTitle());
        subGovGroupDTO.setRuDescriptionTitle(entity.getRuDescriptionTitle());
        subGovGroupDTO.setUzDescription(entity.getUzDescription());
        subGovGroupDTO.setRuDescription(entity.getRuDescription());
        subGovGroupDTO.setKrDescription(entity.getKrDescription());
        subGovGroupDTO.setKrTitle(entity.getKrTitle());
        subGovGroupDTO.setRuTitle(entity.getRuTitle());
        subGovGroupDTO.setUzTitle(entity.getUzTitle());
        subGovGroupDTO.setId(entity.getId());
        subGovGroupDTO.setRank(entity.getRank());
        subGovGroupDTO.setSubmenuId(entity.getSubmenu().getId());
        subGovGroupDTO.setOrderList(stateEmployeeMapper.toDTO(entity.getEmployeeList()));
        return subGovGroupDTO;
    }


    public List<SubGovGroupDTO> toDto(List<SubGovGroup> entities) {
        List<SubGovGroupDTO> res = new ArrayList<>();
        for (SubGovGroup g : entities) {
            res.add(toDto(g));
        }
        return res;
    }


    public SubGovGroup fromCreateDto(SubGovGroupCreateDTO createDto, int rank) {
        SubGovGroup subGovGroup = new SubGovGroup();
        subGovGroup.setUzName(createDto.getUzName());
        subGovGroup.setKrName(createDto.getKrName());
        subGovGroup.setRuName(createDto.getRuName());
        subGovGroup.setKrDescriptionTitle(createDto.getKrDescriptionTitle());
        subGovGroup.setUzDescriptionTitle(createDto.getUzDescriptionTitle());
        subGovGroup.setRuDescriptionTitle(createDto.getRuDescriptionTitle());
        subGovGroup.setKrDescription("бўш");
        subGovGroup.setUzDescription("bo'sh");
        subGovGroup.setRuDescription("пустой");
        subGovGroup.setKrTitle(createDto.getKrTitle());
        subGovGroup.setRuTitle(createDto.getRuTitle());
        subGovGroup.setUzTitle(createDto.getUzTitle());
        subGovGroup.setRank(rank);
        //
        Admin sessionUser = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        subGovGroup.setCreatedBy(sessionUser.getId());
        subGovGroup.setCreatedAt(LocalDateTime.now());
        //
        return subGovGroup;
    }


}
