package uz.dariko.collections.sector;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.file.File;
import uz.dariko.collections.region.Region;
import uz.dariko.collections.sector.dto.SectorCreateDTO;
import uz.dariko.collections.sector.dto.SectorDtoForHome;
import uz.dariko.collections.stateEmloyee.StateEmployee;
import uz.dariko.response.Data;
import uz.dariko.utils.BaseUtils;
import uz.dariko.utils.EntityGetter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SectorService implements BaseService {

    private final SectorRepository sectorRepository;

    private final EntityGetter entityGetter;

    private final SectorMapper sectorMapper;

    private final BaseUtils baseUtils;

    public SectorService(SectorRepository sectorRepository, EntityGetter entityGetter, SectorMapper sectorMapper, BaseUtils baseUtils) {
        this.sectorRepository = sectorRepository;
        this.entityGetter = entityGetter;
        this.sectorMapper = sectorMapper;
        this.baseUtils = baseUtils;
    }

    public ResponseEntity<Data<List<SectorDtoForHome>>> getById(String id) {

        UUID uuid = baseUtils.parseUUID(id);

        Optional<List<Sector>> allById = sectorRepository.findAllById(uuid);
        if(allById.isPresent()){
            List<Sector> sectors = allById.get();
            List<SectorDtoForHome> sectorDTOS = sectorMapper.toHomeDto(sectors);
            return ResponseEntity.ok(new Data<>(sectorDTOS));
        }
        return ResponseEntity.badRequest().body(new Data<>(false));
    }

    public ResponseEntity<?> create(SectorCreateDTO sectorCreateDTO) {

        UUID fileId = baseUtils.parseUUID(sectorCreateDTO.getFileId());
        UUID leaderId = baseUtils.parseUUID(sectorCreateDTO.getStateEmployeeId());
        UUID regionId = baseUtils.parseUUID(sectorCreateDTO.getRegionId());

        File file = entityGetter.getFile(fileId);
        StateEmployee stateEmployee = entityGetter.getStateEmployee(leaderId);
        Region region = entityGetter.getRegion(regionId);
        Sector sector = sectorMapper.fromCreateDto(sectorCreateDTO);
        sector.setFile(file);
        sector.setStateEmployee(stateEmployee);
        sector.setRegion(region);
        sectorRepository.save(sector);
        return ResponseEntity.status(201).body("success");

    }

}
