package uz.dariko.collections.infoGroup;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.dto.BaseOrderDTO;
import uz.dariko.base.dto.OrderDTO;
import uz.dariko.base.dto.SubMenuAdminDTO;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.govGroup.GovGroup;
import uz.dariko.collections.infoGroup.dto.InfoGroupCreateDTO;
import uz.dariko.collections.infoGroup.dto.InfoGroupDTO;
import uz.dariko.collections.infoGroup.dto.InfoGroupUpdateDTO;
import uz.dariko.response.Data;
import uz.dariko.utils.EntityGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class InfoGroupService implements BaseService {

    private final InfoGroupRepository infoGroupRepository;

    private final InfoGroupMapper infoGroupMapper;

    private final EntityGetter entityGetter;


    public InfoGroupService(InfoGroupRepository infoGroupRepository, InfoGroupMapper infoGroupMapper, EntityGetter entityGetter) {
        this.infoGroupRepository = infoGroupRepository;
        this.infoGroupMapper = infoGroupMapper;
        this.entityGetter = entityGetter;
    }

    public ResponseEntity<?> create(InfoGroupCreateDTO infoGroupCreateDto) {
        InfoGroup infoGroup = infoGroupMapper.fromCreateDto(infoGroupCreateDto);
        infoGroup.setMenu(entityGetter.getMenu(infoGroupCreateDto.getMenuId()));
        infoGroupRepository.save(infoGroup);
        return ResponseEntity.status(201).body("saved");
    }

    public ResponseEntity<?> getAll() {
        List<InfoGroup> all = infoGroupRepository.findAllByDeleted(false);
        List<InfoGroupDTO> infoGroupDTOS = infoGroupMapper.toDtoList(all);
        return ResponseEntity.ok(infoGroupDTOS);
    }

    public ResponseEntity<?> update(InfoGroupUpdateDTO dto){

        InfoGroup infoGroup = entityGetter.getInfoGroup(dto.getId());
        infoGroup.setUzName(dto.getUzName());
        infoGroup.setKrName(dto.getKrName());
        infoGroup.setRuName(dto.getRuName());
        infoGroup.setRank(dto.getRank());
        infoGroup.setMenu(entityGetter.getMenu(dto.getMenuId()));
        InfoGroup save = infoGroupRepository.save(infoGroup);
        InfoGroupDTO infoGroupDTO = infoGroupMapper.toDto(save);
        return ResponseEntity.ok(infoGroupDTO);

    }

    public ResponseEntity<?> get(UUID id) {
        InfoGroup infoGroup = entityGetter.getInfoGroup(id);
        InfoGroupDTO infoGroupDTO = infoGroupMapper.toDto(infoGroup);
        return ResponseEntity.ok(infoGroupDTO);
    }

    public ResponseEntity<?> delete(UUID id) {
        InfoGroup infoGroup = entityGetter.getInfoGroup(id);
        infoGroup.setDeleted(true);
        infoGroupRepository.save(infoGroup);
        return ResponseEntity.ok(true);

    }

    public List<SubMenuAdminDTO> getForAdmin(){
        List<InfoGroup> list = infoGroupRepository.findAllByDeleted(false);
        return infoGroupMapper.toAdminDto(list);
    }

    public ResponseEntity<Data<List<InfoGroupDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
        List<OrderDTO> orderDTOS = baseOrderDTO.getOrders();
        List<InfoGroup> entities = new ArrayList<>();
        for (OrderDTO order : orderDTOS) {
            InfoGroup entity = infoGroupRepository.setOrOrderNumber(order.getId(), order.getOrder());
            entities.add(entity);
        }
        return new ResponseEntity<>(new Data<>(infoGroupMapper.toDto(entities)), HttpStatus.OK);
    }
}
