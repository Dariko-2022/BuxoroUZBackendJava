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
import uz.dariko.collections.sector.dto.SectorCreateDTO;
import uz.dariko.collections.sector.dto.SectorDTO;
import uz.dariko.collections.sector.dto.SectorUpdateDTO;
import uz.dariko.collections.stateEmloyee.StateEmployee;
import uz.dariko.collections.stateEmloyee.StateEmployeeRepository;
import uz.dariko.utils.BaseUtils;
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

    private final BaseUtils baseUtils;

    private final StateEmployeeRepository stateEmployeeRepository;

    public SectorService(SectorRepository sectorRepository, EntityGetter entityGetter, SectorMapper sectorMapper, BaseUtils baseUtils, StateEmployeeRepository stateEmployeeRepository) {
        this.sectorRepository = sectorRepository;
        this.entityGetter = entityGetter;
        this.sectorMapper = sectorMapper;
        this.baseUtils = baseUtils;
        this.stateEmployeeRepository = stateEmployeeRepository;
    }

    public ResponseEntity<?> create(SectorCreateDTO dto) {
        return null;

    }

    public ResponseEntity<?> get() {
        List<Sector> list = sectorRepository.findAllByDeletedNot();
        List<SectorDTO> sectorDTOS = sectorMapper.toDto(list);
        return ResponseEntity.ok(sectorDTOS);
    }

    public ResponseEntity<?> get(String code) {
        UUID id = baseUtils.parseUUID(code);
        Sector sector = entityGetter.getSector(id);
        SectorDTO sectorDTO = sectorMapper.toDto(sector);
        return ResponseEntity.ok(sectorDTO);
    }

    public ResponseEntity<?> update(SectorUpdateDTO dto) {
        Sector sector0 = entityGetter.getSector(dto.getId());
        Sector sector = sectorMapper.fromUpdateDto(dto, sector0);
        File file = null;
        if(dto.getFileId()!=null){
            file = entityGetter.getFile(dto.getFileId());
        }
        sector.setStateEmployee(null);
        sector.setFile(file);
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sector.setUpdatedBy(sessionUser.getId());
        sector.setUpdatedAt(LocalDateTime.now());
        Sector save = sectorRepository.save(sector);
        SectorDTO sectorDTO = sectorMapper.toDto(save);
        return ResponseEntity.ok(sectorDTO);
    }

    public ResponseEntity<?> updateStateEmployee(StateEmployee stateEmployee,UUID sectorID) {
        Sector sector = entityGetter.getSector(sectorID);
        sector.setStateEmployee(stateEmployee);
        Sector save = sectorRepository.save(sector);
        SectorDTO sectorDTO = sectorMapper.toDto(save);
        return ResponseEntity.ok(sectorDTO);
    }

    public ResponseEntity<?> delete(String code) {
        UUID uuid = baseUtils.parseUUID(code);
        Sector sector = entityGetter.getSector(uuid);
        sector.setDeleted(true);
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        sector.setDeletedBy(sessionUser.getId());
        sector.setDeletedAt(LocalDateTime.now());
        sectorRepository.save(sector);
        return ResponseEntity.ok(true);
    }

    public ResponseEntity<?> deleteEmployee(String sectorId) {
        UUID sectorID = baseUtils.parseUUID(sectorId);
        Sector sector = entityGetter.getSector(sectorID);
        StateEmployee stateEmployee = sector.getStateEmployee();
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        stateEmployee.setDeletedAt(LocalDateTime.now());
        stateEmployee.setDeleted(true);
        stateEmployee.setDeletedBy(sessionUser.getId());
        stateEmployeeRepository.save(stateEmployee);
        sector.setStateEmployee(null);
        sectorRepository.save(sector);
        return ResponseEntity.ok("Success");
    }

    public ResponseEntity<?> changeOrder(BaseOrderDTO baseOrderDTO) {
        List<OrderDTO> orderDTOS = baseOrderDTO.getOrders();
        List<Sector> entities = new ArrayList<>();
        for (OrderDTO order : orderDTOS) {
            Sector entity = sectorRepository.setOrOrderNumber(order.getId(), order.getOrder());
            entities.add(entity);
        }
        return new ResponseEntity<>(sectorMapper.toDto(entities), HttpStatus.OK);
    }
}
