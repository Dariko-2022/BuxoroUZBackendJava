package uz.dariko.collections.sphere;

import org.mapstruct.Mapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.dariko.base.dto.SubMenuAdminDTO;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.sphere.dto.SphereUpdateDTO;
import uz.dariko.collections.sphere.dto.SphereCreateDTO;
import uz.dariko.collections.sphere.dto.SphereDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface SphereMapper extends AbstractMapper<SphereCreateDTO, SphereUpdateDTO, SphereDTO, Sphere> {
    @Override
    default SphereDTO toDto(Sphere entity){
        SphereDTO sphereDTO = new SphereDTO();
        sphereDTO.setId(entity.getId());
        sphereDTO.setUzName(entity.getUzName());
        sphereDTO.setKrName(entity.getKrName());
        sphereDTO.setRuName(entity.getRuName());
        sphereDTO.setMenuId(entity.getMenu().getId());
        sphereDTO.setRank(entity.getRank());
        sphereDTO.setType("news");
        sphereDTO.setVisible(entity.isVisible());
        return sphereDTO;
    }

    @Override
    default Sphere fromCreateDto(SphereCreateDTO createDto){
        Sphere sphere = new Sphere();
        //
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sphere.setCreatedBy(sessionUser.getId());
        sphere.setCreatedAt(LocalDateTime.now());
        //
        sphere.setKrName(createDto.getKrName());
        sphere.setRuName(createDto.getRuName());
        sphere.setUzName(createDto.getUzName());
        sphere.setVisible(createDto.isVisible());
        return sphere;
    }

    default List<SubMenuAdminDTO> toAdminDto(List<Sphere> list) {
        List<SubMenuAdminDTO> res = new ArrayList<>();
        for(Sphere sphere : list) {
            res.add(toAdminDto(sphere));
        }
        return res;
    }

    default SubMenuAdminDTO toAdminDto(Sphere sphere) {
        SubMenuAdminDTO subMenuAdminDTO = new SubMenuAdminDTO();
        subMenuAdminDTO.setId(sphere.getId());
        subMenuAdminDTO.setRank(sphere.getRank());
        subMenuAdminDTO.setKrName(sphere.getKrName());
        subMenuAdminDTO.setUzName(sphere.getUzName());
        subMenuAdminDTO.setRuName(sphere.getRuName());
        subMenuAdminDTO.setMenuId(sphere.getMenu().getId());
        subMenuAdminDTO.setType("news");
        subMenuAdminDTO.setVisible(sphere.isVisible());
        return subMenuAdminDTO;
    }
}
