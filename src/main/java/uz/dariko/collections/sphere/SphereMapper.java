package uz.dariko.collections.sphere;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.sphere.dto.SphereCreateDTO;
import uz.dariko.collections.sphere.dto.SphereDTO;
import uz.dariko.collections.sphere.dto.SphereUpdateDTO;

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
        return sphereDTO;
    }

    @Override
    default Sphere fromCreateDto(SphereCreateDTO createDto){
        Sphere sphere = new Sphere();
        sphere.setKrName(createDto.getKrName());
        sphere.setRuName(createDto.getRuName());
        sphere.setUzName(createDto.getUzName());
        return sphere;
    }
}
