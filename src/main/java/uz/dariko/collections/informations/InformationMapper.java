package uz.dariko.collections.informations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.BaseMapper;
import uz.dariko.collections.file.File;
import uz.dariko.collections.infoGroup.InfoGroup;
import uz.dariko.collections.informations.dto.InformationCreateDTO;
import uz.dariko.collections.informations.dto.InformationDTO;
import uz.dariko.collections.informations.dto.InformationUpdateDTO;
import uz.dariko.utils.EntityGetter;

import java.util.ArrayList;
import java.util.List;

@Component
public class InformationMapper implements BaseMapper {

    @Autowired
    EntityGetter entityGetter;
     InformationDTO toDto(Information entity){
        return new InformationDTO(entity.getUzTitle(),
                entity.getKrTitle(),
                entity.getRuTitle(),
                entity.getUzDescription(),
                entity.getKrDescription(),
                entity.getRuDescription(),
                entity.getUzBody(),
                entity.getKrBody(),
                entity.getRuBody(),
                entityGetter.getGeneratedNames(entity.getImages()),
                entity.getInfoGroup().getId(),
                entity.getSource());
    };


    Information fromCreateDto(InformationCreateDTO createDto){
        return new Information(createDto.getUzTitle(),
                createDto.getKrTitle(),
                createDto.getRuTitle(),
                createDto.getUzDescription(),
                createDto.getKrDescription(),
                createDto.getRuDescription(),
                createDto.getUzBody(),
                createDto.getKrBody(),
                createDto.getRuBody(),
                entityGetter.getInfoGroup(createDto.getInfoGroupID()),
                entityGetter.getFiles(createDto.getImageIDs()),
                createDto.getSource());
    };


    Information fromUpdateDto(InformationUpdateDTO updateDto){
        Information information = entityGetter.getInformation(updateDto.getId());
        List<File> images = entityGetter.getFiles(updateDto.getImageIDs());
        InfoGroup infoGroup = entityGetter.getInfoGroup(updateDto.getInfoGroupID());
        information.setUzTitle(updateDto.getUzTitle());
        information.setKrTitle(updateDto.getKrTitle());
        information.setRuTitle(updateDto.getRuTitle());
        information.setUzDescription(updateDto.getUzDescription());
        information.setKrDescription(updateDto.getKrDescription());
        information.setRuDescription(updateDto.getRuDescription());
        information.setUzBody(updateDto.getUzBody());
        information.setKrBody(updateDto.getKrBody());
        information.setRuBody(updateDto.getRuBody());
        information.setInfoGroup(infoGroup);
        information.setImages(images);
        information.setSource(updateDto.getSource());
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
