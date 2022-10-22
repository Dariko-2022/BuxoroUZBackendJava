package uz.dariko.collections.govGroup;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.dto.BaseOrderDTO;
import uz.dariko.base.dto.OrderDTO;
import uz.dariko.base.dto.SubMenuAdminDTO;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.admin.dto.AdminDTO;
import uz.dariko.collections.govGroup.dto.GovGroupCreateDTO;
import uz.dariko.collections.govGroup.dto.GovGroupDTO;
import uz.dariko.collections.govGroup.dto.GovGroupUpdateDTO;
import uz.dariko.collections.stateEmloyee.StateEmployee;
import uz.dariko.response.Data;
import uz.dariko.utils.EntityGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GovGroupService implements BaseService {

    private final GovGroupRepository govGroupRepository;

    private final GovGroupMapper govGroupMapper;

    private final EntityGetter entityGetter;


    public GovGroupService(GovGroupRepository govGroupRepository, GovGroupMapper govGroupMapper, EntityGetter entityGetter) {
        this.govGroupRepository = govGroupRepository;
        this.govGroupMapper = govGroupMapper;
        this.entityGetter = entityGetter;
    }

    public ResponseEntity<?> create(GovGroupCreateDTO dto) {
        GovGroup govGroup = govGroupMapper.fromCreateDto(dto);
        govGroup.setMenu(entityGetter.getMenu(dto.getMenuId()));
        GovGroup save = govGroupRepository.save(govGroup);
        GovGroupDTO govGroupDTO = govGroupMapper.toDto(save);
        return ResponseEntity.status(201).body(govGroupDTO);
    }

    public ResponseEntity<?> update(GovGroupUpdateDTO dto) {

        GovGroup govGroup = entityGetter.getGovGroup(dto.getId());
        govGroup.setUzName(dto.getUzName());
        govGroup.setKrName(dto.getKrName());
        govGroup.setRuName(dto.getRuName());
        govGroup.setRank(dto.getRank());
        govGroup.setVisible(dto.isVisible());
        govGroup.setMenu(entityGetter.getMenu(dto.getMenuId()));
        govGroupRepository.save(govGroup);
        return ResponseEntity.status(204).body("updated");
    }

    public ResponseEntity<?> delete(UUID id) {

        GovGroup govGroup = entityGetter.getGovGroup(id);
        govGroup.setDeleted(true);
        govGroupRepository.save(govGroup);
        return ResponseEntity.status(204).body(new Data<>(true) );
    }

    public ResponseEntity<?> getById(UUID id) {

        GovGroup govGroup = entityGetter.getGovGroup(id);
        GovGroupDTO govGroupDTO = govGroupMapper.toDto(govGroup);
        return ResponseEntity.status(200).body(govGroupDTO);


    }

    public ResponseEntity<?> getList() {
        List<GovGroup> list = govGroupRepository.findAllByDeleted(false);
        List<GovGroupDTO> govGroupDTOS = govGroupMapper.toDto(list);
        return ResponseEntity.ok(govGroupDTOS);

    }

    public List<SubMenuAdminDTO> getForAdmin(){
        List<GovGroup> list = govGroupRepository.findAllByDeleted(false);
        return govGroupMapper.toAdminDto(list);
    }

    public ResponseEntity<Data<List<GovGroupDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
        List<OrderDTO> orderDTOS = baseOrderDTO.getOrders();
        List<GovGroup> entities = new ArrayList<>();
        for (OrderDTO order : orderDTOS) {
            GovGroup entity = govGroupRepository.setOrOrderNumber(order.getId(), order.getOrder());
            entities.add(entity);
        }
        return new ResponseEntity<>(new Data<>(govGroupMapper.toDto(entities)), HttpStatus.OK);
    }
}
