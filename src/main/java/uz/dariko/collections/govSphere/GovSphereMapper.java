package uz.dariko.collections.govSphere;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.govSphere.dto.GovSphereCreateDTO;
import uz.dariko.collections.govSphere.dto.GovSphereDTO;
import uz.dariko.collections.govSphere.dto.GovSphereUpdateDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface GovSphereMapper extends AbstractMapper<GovSphereCreateDTO, GovSphereUpdateDTO, GovSphereDTO, GovSphere> {
    @Override
    default GovSphereDTO toDto(GovSphere entity) {
        GovSphereDTO govSphereDTO = new GovSphereDTO(entity.getUzName(), entity.getKrName(), entity.getRuName());
        govSphereDTO.setId(entity.getId());
        return govSphereDTO;

    }

    default List<GovSphereDTO> toDtoList(List<GovSphere> entities){
        List<GovSphereDTO> res = new ArrayList<>();
        for (GovSphere govSphere : entities) {
            res.add(toDto(govSphere));
        }
        return res;
    }

    @Override
    default GovSphere fromCreateDto(GovSphereCreateDTO createDto) {
        GovSphere govSphere = new GovSphere(
                createDto.getUzName(),
                createDto.getKrName(),
                createDto.getRuName()
        );
        return govSphere;
    }


}
