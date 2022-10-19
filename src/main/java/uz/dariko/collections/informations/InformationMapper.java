package uz.dariko.collections.informations;

import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.informations.dto.InformationCreateDTO;
import uz.dariko.collections.informations.dto.InformationDTO;
import uz.dariko.collections.informations.dto.InformationUpdateDTO;
import uz.dariko.utils.EntityGetter;

import java.util.List;
import java.util.UUID;

/*
@Component
public class InformationMapper {

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
        new Information(createDto.getUzTitle(),
                createDto.getKrTitle(),
                createDto.getRuTitle(),
                createDto.getUzDescription(),
                createDto.getKrDescription(),
                createDto.getRuDescription(),
                createDto.getUzBody(),
                createDto.getKrBody(),
                createDto.getRuBody(),

                entityGetter.getFiles(createDto.getImageIDs()),
                )
    };


    Information fromUpdateDto(InformationUpdateDTO updateDto);


    List<InformationDTO> toDto(List<Information> entities);
}
*/
