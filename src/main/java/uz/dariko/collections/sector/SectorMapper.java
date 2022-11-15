package uz.dariko.collections.sector;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.BaseMapper;
import uz.dariko.collections.file.File;
import uz.dariko.collections.sector.dto.SectorDTO;
import uz.dariko.collections.sector.dto.SectorDtoForHome;
import uz.dariko.collections.sector.dto.SectorUpdateDTO;
import uz.dariko.collections.stateEmloyee.StateEmployee;
import uz.dariko.collections.stateEmloyee.StateEmployeeMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class SectorMapper implements BaseMapper {

    private final StateEmployeeMapper stateEmployeeMapper;

    public SectorMapper(StateEmployeeMapper stateEmployeeMapper) {
        this.stateEmployeeMapper = stateEmployeeMapper;
    }


    public SectorDTO toDto(Sector sector){
        SectorDTO sectorDTO = new SectorDTO();
        File file = sector.getFile();
        if(file != null) {
            sectorDTO.setImageID(file.getId());
        }else sectorDTO.setImageID(null);
        StateEmployee stateEmployee = sector.getStateEmployee();
        if(stateEmployee != null) {
            sectorDTO.setStateEmployeeDTO(stateEmployeeMapper.toDTO(sector.getStateEmployee()));
        }else sectorDTO.setStateEmployeeDTO(null);
        sectorDTO.setId(sector.getId());
        sectorDTO.setUzName(sector.getUzName());
        sectorDTO.setKrName(sector.getKrName());
        sectorDTO.setRuName(sector.getRuName());
        sectorDTO.setUzSectorArea(sector.getUzSectorArea());
        sectorDTO.setKrSectorArea(sector.getKrSectorArea());
        sectorDTO.setRuSectorArea(sector.getRuSectorArea());
        sectorDTO.setUzDescription(sector.getUzDescription());
        sectorDTO.setKrDescription(sector.getKrDescription());
        sectorDTO.setRuDescription(sector.getRuDescription());
        sectorDTO.setOrderNumber(sector.getOrderNumber());
        return sectorDTO;
    }


    public List<SectorDTO> toDto(List<Sector> entities) {
        List<SectorDTO> sectorDTOS = new ArrayList<>();
        for (Sector sector : entities) {
            sectorDTOS.add(toDto(sector));
        }
        return sectorDTOS;
    }

    public List<SectorDtoForHome> toHomeDto(List<Sector> sectors) {
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


    public Sector fromUpdateDto(SectorUpdateDTO dto,Sector sector){
        sector.setKrDescription(dto.getKrDescription());
        sector.setUzDescription(dto.getUzDescription());
        sector.setRuDescription(dto.getRuDescription());
        sector.setUzName(dto.getUzName());
        sector.setRuName(dto.getRuName());
        sector.setKrName(dto.getKrName());
        sector.setUzSectorArea(dto.getUzSectorArea());
        sector.setKrSectorArea(dto.getKrSectorArea());
        sector.setRuSectorArea(dto.getRuSectorArea());
        return sector;
    }
}
