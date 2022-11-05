package uz.dariko.collections.informations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.BaseMapper;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.file.File;
import uz.dariko.collections.informations.dto.InformationCreateDTO;
import uz.dariko.collections.informations.dto.InformationDTO;
import uz.dariko.collections.informations.dto.InformationUpdateDTO;
import uz.dariko.collections.submenu.Submenu;
import uz.dariko.utils.EntityGetter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class InformationMapper implements BaseMapper {

    @Autowired
    EntityGetter entityGetter;
     InformationDTO toDto(Information entity){
        InformationDTO dto = new InformationDTO();
        dto.setId(entity.getId());
        dto.setUzTitle(entity.getUzTitle());
        dto.setKrTitle(entity.getKrTitle());
        dto.setRuTitle(entity.getRuTitle());
        dto.setUzBody(entity.getUzBody());
        dto.setKrBody(entity.getKrBody());
        dto.setRuBody(entity.getRuBody());
        dto.setUzDescription(entity.getUzDescription());
        dto.setKrDescription(entity.getKrDescription());
        dto.setRuDescription(entity.getRuDescription());
        dto.setGeneratedNames(entityGetter.getIDs(entity.getImages()));
        dto.setSubmenuID(entity.getSubmenu().getId());
        dto.setSource(entity.getSource());
        dto.setCreatedDate(entity.getCreatedAt());
        return dto;
    }
    Information fromCreateDto(InformationCreateDTO createDto){
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Information information= new Information();
        information.setUzTitle(createDto.getUzTitle());
        information.setKrTitle(createDto.getKrTitle());
        information.setRuTitle(createDto.getRuTitle());
        information.setUzDescription(createDto.getUzDescription());
        information.setKrDescription(createDto.getKrDescription());
        information.setRuDescription(createDto.getRuDescription());
        information.setUzBody(createDto.getUzBody());
        information.setKrBody(createDto.getKrBody());
        information.setRuBody(createDto.getRuBody());
        information.setSubmenu(entityGetter.getSubmenu(createDto.getSubmenuID()));
        information.setImages(entityGetter.getFiles(createDto.getImageIDs()));
        information.setSource(createDto.getSource());
        information.setCreatedBy(sessionUser.getId());
        information.setCreatedAt(LocalDateTime.now());
        information.setUpdatedBy(sessionUser.getId());
        information.setUpdatedAt(LocalDateTime.now());
        return information;
    };


    Information fromUpdateDto(InformationUpdateDTO updateDto){
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Information information = entityGetter.getInformation(updateDto.getId());
        List<File> images = entityGetter.getFiles(updateDto.getImageIDs());
        Submenu submenu = entityGetter.getSubmenu(updateDto.getSubmenuID());
        information.setUzTitle(updateDto.getUzTitle());
        information.setKrTitle(updateDto.getKrTitle());
        information.setRuTitle(updateDto.getRuTitle());
        information.setUzDescription(updateDto.getUzDescription());
        information.setKrDescription(updateDto.getKrDescription());
        information.setRuDescription(updateDto.getRuDescription());
        information.setUzBody(updateDto.getUzBody());
        information.setKrBody(updateDto.getKrBody());
        information.setRuBody(updateDto.getRuBody());
        information.setSubmenu(submenu);
        information.setImages(images);
        information.setSource(updateDto.getSource());
        information.setUpdatedBy(sessionUser.getId());
        information.setUpdatedAt(LocalDateTime.now());

        return information;
    };


    List<InformationDTO> toDto(List<Information> entities){
        List<InformationDTO> informationDTOS=new ArrayList<>();

        for (Information entity : entities) {
            informationDTOS.add(toDto(entity));
        }
        return informationDTOS;
    }
}
