package uz.dariko.collections.informations;

import org.mapstruct.Mapper;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.informations.dto.InformationCreateDTO;
import uz.dariko.collections.informations.dto.InformationDTO;
import uz.dariko.collections.informations.dto.InformationUpdateDTO;

import java.util.List;
import java.util.UUID;

@Mapper
public interface InformationMapper extends AbstractMapper<InformationCreateDTO, InformationUpdateDTO, InformationDTO, Information> {
    @Override
    InformationDTO toDto(Information entity);

    @Override
    Information fromCreateDto(InformationCreateDTO createDto);

    @Override
    Information fromUpdateDto(InformationUpdateDTO updateDto);

    @Override
    List<InformationDTO> toDto(List<Information> entities);
}
