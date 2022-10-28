package uz.dariko.collections.sector;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.dariko.base.dto.BaseOrderDTO;
import uz.dariko.base.dto.OrderDTO;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.file.File;
import uz.dariko.collections.region.Region;
import uz.dariko.collections.sector.dto.SectorCreateDTO;
import uz.dariko.collections.sector.dto.SectorDTO;
import uz.dariko.collections.sector.dto.SectorUpdateDTO;
import uz.dariko.collections.stateEmloyee.StateEmployee;
import uz.dariko.response.Data;
import uz.dariko.utils.EntityGetter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class SectorService implements BaseService {

    private final SectorRepository sectorRepository;

    private final EntityGetter entityGetter;

    private final SectorMapper sectorMapper;


    public SectorService(SectorRepository sectorRepository, EntityGetter entityGetter, SectorMapper sectorMapper) {
        this.sectorRepository = sectorRepository;
        this.entityGetter = entityGetter;
        this.sectorMapper = sectorMapper;
    }

    public ResponseEntity<Data<SectorDTO>> create(SectorCreateDTO dto) {

        File file = entityGetter.getFile(dto.getFileId());
        StateEmployee stateEmployee = entityGetter.getStateEmployee(dto.getStateEmployeeId());
        Region region = entityGetter.getRegion(dto.getRegionId());
        Sector sector = sectorMapper.fromCreateDto(dto);
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sector.setCreatedBy(sessionUser.getId());
        sector.setCreatedAt(LocalDateTime.now());
        sector.setFile(file);
        sector.setStateEmployee(stateEmployee);
        sector.setRegion(region);
        Sector save = sectorRepository.save(sector);
        SectorDTO sectorDTO = sectorMapper.toDto(save);
        return ResponseEntity.status(201).body(new Data<>(sectorDTO));

    }

    public ResponseEntity<Data<List<SectorDTO>>> get() {
        List<Sector> list = sectorRepository.findAllByDeletedNot();
        List<SectorDTO> sectorDTOS = sectorMapper.toDto(list);
        return ResponseEntity.ok(new Data<>(sectorDTOS));
    }

    public ResponseEntity<Data<SectorDTO>> get(UUID id) {
        Sector sector = entityGetter.getSector(id);
        SectorDTO sectorDTO = sectorMapper.toDto(sector);
        return ResponseEntity.ok(new Data<>(sectorDTO));
    }

    public ResponseEntity<Data<SectorDTO>> update(SectorUpdateDTO dto) {
        Sector sector0 = entityGetter.getSector(dto.getId());
        Sector sector = sectorMapper.fromUpdateDto(dto, sector0);
        Region region = entityGetter.getRegion(dto.getRegionId());
        StateEmployee stateEmployee = entityGetter.getStateEmployee(dto.getStateEmployeeId());
        File file = entityGetter.getFile(dto.getFileId());
        sector.setStateEmployee(stateEmployee);
        sector.setRegion(region);
        sector.setFile(file);
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sector.setUpdatedBy(sessionUser.getId());
        sector.setUpdatedAt(LocalDateTime.now());
        Sector save = sectorRepository.save(sector);
        SectorDTO sectorDTO = sectorMapper.toDto(save);
        return ResponseEntity.ok(new Data<>(sectorDTO));
    }

    public ResponseEntity<Data<Boolean>> delete(UUID uuid) {
        Sector sector = entityGetter.getSector(uuid);
        sector.setDeleted(true);
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sector.setDeletedBy(sessionUser.getId());
        sector.setDeletedAt(LocalDateTime.now());
        sectorRepository.save(sector);
        return ResponseEntity.ok(new Data<>(true));
    }

    public ResponseEntity<Data<List<SectorDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
        List<OrderDTO> orderDTOS = baseOrderDTO.getOrders();
        List<Sector> entities = new ArrayList<>();
        for (OrderDTO order : orderDTOS) {
            Sector entity = sectorRepository.setOrOrderNumber(order.getId(), order.getOrder());
            entities.add(entity);
        }
        return new ResponseEntity<>(new Data<>(sectorMapper.toDto(entities)), HttpStatus.OK);
    }
}
