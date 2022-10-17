package uz.dariko.collections.sector;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.sector.dto.SectorCreateDTO;
import uz.dariko.collections.sector.dto.SectorDTO;
import uz.dariko.collections.sector.dto.SectorDtoForHome;
import uz.dariko.collections.sector.dto.SectorUpdateDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface SectorMapper extends AbstractMapper<SectorCreateDTO, SectorUpdateDTO, SectorDTO, Sector> {


    @Override
    default List<SectorDTO> toDto(List<Sector> entities) {
        List<SectorDTO> sectorDTOS = new ArrayList<>();
        for (Sector sector : entities) {
            SectorDTO sectorDTO = new SectorDTO();
            sectorDTO.setId(sector.getId());
            sectorDTO.setUzName(sector.getUzName());
            sectorDTO.setKrName(sector.getKrName());
            sectorDTO.setRuName(sector.getRuName());
            sectorDTO.setSectorArea(sector.getSectorArea());
            sectorDTO.setDescription(sector.getDescription());
            sectorDTO.setGeneratedName(sector.getFile().getGeneratedName());
            sectorDTO.setSectorLeaderId(sector.getStateEmployee().getId());
            sectorDTOS.add(sectorDTO);
        }
        return sectorDTOS;
    }

    default List<SectorDtoForHome> toHomeDto(List<Sector> sectors) {
        List<SectorDtoForHome> res = new ArrayList<>();
        for(Sector sector : sectors) {
            SectorDtoForHome dto = new SectorDtoForHome();
            dto.setGeneratedName(sector.getFile().getGeneratedName());
            dto.setUzName(sector.getUzName());
            dto.setKrName(sector.getKrName());
            dto.setRuName(sector.getRuName());
            res.add(dto);
        }
        return res;
    }

    @Override
    default Sector fromCreateDto(SectorCreateDTO createDto) {
        Sector sector = new Sector();
        sector.setDescription(createDto.getDescription());
        sector.setSectorArea(createDto.getSectorArea());
        sector.setKrName(createDto.getKrName());
        sector.setRuName(createDto.getRuName());
        sector.setUzName(createDto.getUzName());

        return sector;
    }

    default Sector fromUpdateDto(SectorUpdateDTO dto,Sector sector){
        sector.setDescription(dto.getDescription());
        sector.setUzName(dto.getUzName());
        sector.setRuName(dto.getRuName());
        sector.setKrName(dto.getKrName());
        sector.setSectorArea(dto.getSectorArea());
        return sector;
    }
}
