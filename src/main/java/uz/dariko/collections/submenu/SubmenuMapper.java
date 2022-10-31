package uz.dariko.collections.submenu;

import org.mapstruct.Mapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.dariko.base.dto.SubMenuAdminDTO;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.submenu.dto.SubmenuUpdateDTO;
import uz.dariko.collections.submenu.dto.SubmenuCreateDTO;
import uz.dariko.collections.submenu.dto.SubmenuDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface SubmenuMapper extends AbstractMapper<SubmenuCreateDTO, SubmenuUpdateDTO, SubmenuDTO, Submenu> {
    @Override
    default SubmenuDTO toDto(Submenu entity){
        SubmenuDTO submenuDTO = new SubmenuDTO();
        submenuDTO.setId(entity.getId());
        submenuDTO.setUzName(entity.getUzName());
        submenuDTO.setKrName(entity.getKrName());
        submenuDTO.setRuName(entity.getRuName());
        submenuDTO.setMenuId(entity.getMenu().getId());
        submenuDTO.setRank(entity.getRank());
        submenuDTO.setType(entity.getType());
        submenuDTO.setVisible(entity.isVisible());
        return submenuDTO;
    }

    @Override
    default Submenu fromCreateDto(SubmenuCreateDTO createDto){
        Submenu submenu = new Submenu();
        //
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        submenu.setCreatedBy(sessionUser.getId());
        submenu.setCreatedAt(LocalDateTime.now());
        //
        submenu.setKrName(createDto.getKrName());
        submenu.setRuName(createDto.getRuName());
        submenu.setUzName(createDto.getUzName());
        submenu.setType(createDto.getType());
        submenu.setVisible(true);
        return submenu;
    }

    default List<SubMenuAdminDTO> toAdminDto(List<Submenu> list) {
        List<SubMenuAdminDTO> res = new ArrayList<>();
        for(Submenu submenu : list) {
            res.add(toAdminDto(submenu));
        }
        return res;
    }

    default SubMenuAdminDTO toAdminDto(Submenu submenu) {
        SubMenuAdminDTO subMenuAdminDTO = new SubMenuAdminDTO();
        subMenuAdminDTO.setId(submenu.getId());
        subMenuAdminDTO.setRank(submenu.getRank());
        subMenuAdminDTO.setKrName(submenu.getKrName());
        subMenuAdminDTO.setUzName(submenu.getUzName());
        subMenuAdminDTO.setRuName(submenu.getRuName());
        subMenuAdminDTO.setMenuId(submenu.getMenu().getId());
        subMenuAdminDTO.setType("news");
        subMenuAdminDTO.setVisible(submenu.isVisible());
        return subMenuAdminDTO;
    }
}
