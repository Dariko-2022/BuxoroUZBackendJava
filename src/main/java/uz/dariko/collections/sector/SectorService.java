package uz.dariko.collections.sector;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.file.File;
import uz.dariko.collections.file.FileRepository;
import uz.dariko.collections.region.Region;
import uz.dariko.collections.region.RegionRepository;
import uz.dariko.collections.sector.dto.SectorCreateDTO;
import uz.dariko.collections.sector.dto.SectorDtoForHome;
import uz.dariko.collections.stateEmloyee.StateEmployee;
import uz.dariko.collections.stateEmloyee.StateEmployeeRepository;
import uz.dariko.response.Data;
import uz.dariko.utils.BaseUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SectorService implements BaseService {

    SectorRepository sectorRepository;

    FileRepository fileRepository;

    RegionRepository regionRepository;

    StateEmployeeRepository stateEmployeeRepository;

    SectorMapper sectorMapper;

    BaseUtils baseUtils;

    public ResponseEntity<Data<List<SectorDtoForHome>>> getById(String id) {

        UUID uuid = baseUtils.parseUUID(id);

        Optional<List<Sector>> allById = sectorRepository.findAllById(uuid);
        if(allById.isPresent()){
            List<Sector> sectors = allById.get();
            List<SectorDtoForHome> sectorDTOS = sectorMapper.toHomeDto(sectors);
            return ResponseEntity.ok(new Data<>(sectorDTOS));
        }


        return null;
    }

    public ResponseEntity<?> create(SectorCreateDTO sectorCreateDTO) {

        UUID fileId = baseUtils.parseUUID(sectorCreateDTO.getFileId());
        UUID leaderId = baseUtils.parseUUID(sectorCreateDTO.getStateEmployeeId());
        UUID regionId = baseUtils.parseUUID(sectorCreateDTO.getRegionId());

        Optional<File> fileOptional = fileRepository.findById(fileId);
        Optional<StateEmployee> leaderOptional = stateEmployeeRepository.findById(leaderId);
        Optional<Region> regionOptional = regionRepository.findById(regionId);
        if(fileOptional.isPresent() & leaderOptional.isPresent() & regionOptional.isPresent()) {
            Sector sector = sectorMapper.fromCreateDto(sectorCreateDTO);
            sector.setFile(fileOptional.get());
            sector.setStateEmployee(leaderOptional.get());
            sector.setRegion(regionOptional.get());
            sectorRepository.save(sector);
            return ResponseEntity.status(201).body("success");
        }
        return ResponseEntity.badRequest().body("Region or Leader or File invalid");

    }

}
