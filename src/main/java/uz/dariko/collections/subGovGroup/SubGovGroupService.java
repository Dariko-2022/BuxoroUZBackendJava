package uz.dariko.collections.subGovGroup;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.order.GovGroupOrder;
import uz.dariko.collections.order.GovGroupOrderService;
import uz.dariko.collections.subGovGroup.dto.SubGovGroupCreateDTO;
import uz.dariko.collections.subGovGroup.dto.SubGovGroupDTO;
import uz.dariko.collections.subGovGroup.dto.SubGovGroupUpdateDTO;
import uz.dariko.base.service.BaseService;
import uz.dariko.response.Data;
import uz.dariko.utils.EntityGetter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SubGovGroupService implements BaseService {

    private final SubGovGroupRepository subGovGroupRepository;

    private final SubGovGroupMapper subGovGroupMapper;

    private final EntityGetter entityGetter;

    private final GovGroupOrderService govGroupOrderService;


    public SubGovGroupService(SubGovGroupRepository govGroupRepository, SubGovGroupMapper subGovGroupMapper, EntityGetter entityGetter, GovGroupOrderService govGroupOrderService) {
        this.subGovGroupRepository = govGroupRepository;
        this.subGovGroupMapper = subGovGroupMapper;
        this.entityGetter = entityGetter;
        this.govGroupOrderService = govGroupOrderService;

    }

    public ResponseEntity<?> create(SubGovGroupCreateDTO dto) {
        SubGovGroup subGovGroup = subGovGroupMapper.fromCreateDto(dto);
        List<GovGroupOrder> govGroupOrders = govGroupOrderService.create(dto.getOrderList());
        subGovGroup.setEmployeeList(govGroupOrders);
        subGovGroup.setSubmenu(entityGetter.getSubmenu(dto.getSubmenuId()));
        SubGovGroup save = subGovGroupRepository.save(subGovGroup);
        SubGovGroupDTO govGroupDTO = subGovGroupMapper.toDto(save);
        return ResponseEntity.status(201).body(govGroupDTO);
    }

    public ResponseEntity<?> update(SubGovGroupUpdateDTO dto) {

        SubGovGroup govGroup = entityGetter.getSubGovGroup(dto.getId());
        govGroup.setUzName(dto.getUzName());
        govGroup.setKrName(dto.getKrName());
        govGroup.setRuName(dto.getRuName());
        govGroup.setUzDescription(dto.getUzDescription());
        govGroup.setRuDescription(dto.getRuDescription());
        govGroup.setKrDescription(dto.getKrDescription());
        govGroup.setEmployeeList(govGroupOrderService.update(dto.getOrderList()));
        //
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        govGroup.setUpdatedBy(sessionUser.getId());
        govGroup.setUpdatedAt(LocalDateTime.now());
        //
        govGroup.setRank(dto.getRank());
        govGroup.setSubmenu(entityGetter.getSubmenu(dto.getSubmenuId()));
        subGovGroupRepository.save(govGroup);
        return ResponseEntity.status(204).body("updated");
    }

    public ResponseEntity<?> delete(UUID id) {

        SubGovGroup subGovGroup = entityGetter.getSubGovGroup(id);
        //
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        subGovGroup.setDeletedBy(sessionUser.getId());
        subGovGroup.setDeletedAt(LocalDateTime.now());
        //
        subGovGroup.setDeleted(true);
        subGovGroupRepository.save(subGovGroup);
        return ResponseEntity.status(204).body(new Data<>(true) );
    }

    public ResponseEntity<?> getById(UUID id) {

        SubGovGroup subGovGroup = entityGetter.getSubGovGroup(id);
        SubGovGroupDTO subGovGroupDTO = subGovGroupMapper.toDto(subGovGroup);
        return ResponseEntity.status(200).body(subGovGroupDTO);


    }

    public ResponseEntity<?> getList() {
        List<SubGovGroup> list = subGovGroupRepository.findAllByDeleted(false);
        List<SubGovGroupDTO> subGovGroupDTOS = subGovGroupMapper.toDto(list);
        return ResponseEntity.ok(subGovGroupDTOS);

    }

}
