package uz.dariko.collections.subGovGroup;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.dto.SubMenuAdminDTO;
import uz.dariko.collections.subGovGroup.dto.SubGovGroupCreateDTO;
import uz.dariko.collections.subGovGroup.dto.SubGovGroupDTO;
import uz.dariko.collections.subGovGroup.dto.SubGovGroupUpdateDTO;
import uz.dariko.base.service.BaseService;
import uz.dariko.response.Data;
import uz.dariko.utils.EntityGetter;

import java.util.List;
import java.util.UUID;

@Service
public class SubGovGroupService implements BaseService {

    private final SubGovGroupRepository subGovGroupRepository;

    private final SubGovGroupMapper subGovGroupMapper;

    private final EntityGetter entityGetter;


    public SubGovGroupService(SubGovGroupRepository govGroupRepository, SubGovGroupMapper subGovGroupMapper, EntityGetter entityGetter) {
        this.subGovGroupRepository = govGroupRepository;
        this.subGovGroupMapper = subGovGroupMapper;
        this.entityGetter = entityGetter;
    }

    public ResponseEntity<?> create(SubGovGroupCreateDTO dto) {
        SubGovGroup subGovGroup = subGovGroupMapper.fromCreateDto(dto);
        subGovGroup.setGovGroup(entityGetter.getGovGroup(dto.getGovGroupId()));
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
        govGroup.setRank(dto.getRank());
        govGroup.setVisible(dto.isVisible());
        govGroup.setGovGroup(entityGetter.getGovGroup(dto.getGovGroupId()));
        subGovGroupRepository.save(govGroup);
        return ResponseEntity.status(204).body("updated");
    }

    public ResponseEntity<?> delete(UUID id) {

        SubGovGroup subGovGroup = entityGetter.getSubGovGroup(id);
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
