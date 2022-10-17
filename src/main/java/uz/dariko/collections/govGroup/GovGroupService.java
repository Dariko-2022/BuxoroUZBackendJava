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

        Optional<GovGroup> byNameAndDeletedNot = govGroupRepository.findByNameAndIsDeleted(dto.getName(),false);

        if(byNameAndDeletedNot.isPresent()) {
            return ResponseEntity.status(409).body("Bu nom oldin ishlatilgan");
        }

        GovGroup govGroup = new GovGroup();
        govGroup.setName(dto.getName());
        govGroup.setDescription(dto.getDescription());
        govGroupRepository.save(govGroup);
        return ResponseEntity.status(201).body("created");
    }

    public ResponseEntity<?> update(GovGroupUpdateDTO dto) {

        GovGroup govGroup = entityGetter.getGovGroup(dto.getId());
        govGroup.setName(dto.getName());
        govGroup.setDescription(dto.getDescription());
        govGroupRepository.save(govGroup);
        return ResponseEntity.status(204).body("updated");
    }

    public ResponseEntity<Data<Boolean>> delete(UUID id) {

        GovGroup govGroup = entityGetter.getGovGroup(id);
        govGroup.setDeleted(true);
        govGroupRepository.save(govGroup);
        return ResponseEntity.status(204).body(new Data<>(true) );
    }

    public ResponseEntity<Data<GovGroupDTO>> getById(UUID id) {

        GovGroup govGroup = entityGetter.getGovGroup(id);
        GovGroupDTO govGroupDTO = new GovGroupDTO(govGroup.getId(),govGroup.getName(),govGroup.getDescription());
        return ResponseEntity.status(200).body(new Data<>(govGroupDTO));


    }

    public ResponseEntity<Data<List<GovGroupDTO>>> getList() {
        List<GovGroup> list = govGroupRepository.findAllByDeleted(false);
        List<GovGroupDTO> govGroupDTOS = govGroupMapper.toDto(list);
        return ResponseEntity.ok(new Data<>(govGroupDTOS));

    }
}
