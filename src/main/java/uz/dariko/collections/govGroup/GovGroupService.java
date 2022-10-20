package uz.dariko.collections.govGroup;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.govGroup.dto.GovGroupCreateDTO;
import uz.dariko.collections.govGroup.dto.GovGroupDTO;
import uz.dariko.collections.govGroup.dto.GovGroupUpdateDTO;
import uz.dariko.response.Data;
import uz.dariko.utils.EntityGetter;

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
        govGroupRepository.save(govGroup);
        return ResponseEntity.status(201).body("created");
    }

    public ResponseEntity<?> update(GovGroupUpdateDTO dto) {

        GovGroup govGroup = entityGetter.getGovGroup(dto.getId());
        govGroup.setUzName(dto.getUzName());
        govGroup.setKrName(dto.getKrName());
        govGroup.setRuName(dto.getRuName());
        govGroup.setDescription(dto.getDescription());
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
}
