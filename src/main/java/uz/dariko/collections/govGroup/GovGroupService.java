package uz.dariko.collections.govGroup;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.govGroup.dto.GovGroupCreateDTO;
import uz.dariko.collections.govGroup.dto.GovGroupDTO;
import uz.dariko.collections.govGroup.dto.GovGroupUpdateDTO;
import uz.dariko.response.Data;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GovGroupService implements BaseService {

    private final GovGroupRepository govGroupRepository;

    private final GovGroupMapper govGroupMapper;


    public GovGroupService(GovGroupRepository govGroupRepository, GovGroupMapper govGroupMapper) {
        this.govGroupRepository = govGroupRepository;
        this.govGroupMapper = govGroupMapper;
    }

    public ResponseEntity<?> create(GovGroupCreateDTO dto) {

        Optional<GovGroup> byNameAndDeletedNot = govGroupRepository.findByNameAndDeletedNot(dto.getName());

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

        Optional<GovGroup> byNameAndDeletedNot = govGroupRepository.findByNameAndDeletedNot(dto.getName());

        if(byNameAndDeletedNot.isPresent()) {
            GovGroup govGroup = byNameAndDeletedNot.get();
            govGroup.setName(dto.getName());
            govGroup.setDescription(dto.getDescription());
            govGroupRepository.save(govGroup);
            return ResponseEntity.status(204).body("updated");
        }

        return ResponseEntity.status(404).body("Not Found");
    }

    public ResponseEntity<Data<Boolean>> delete(UUID id) {
        Optional<GovGroup> byId = govGroupRepository.findById(id);
        if(byId.isPresent()){
            GovGroup govGroup = byId.get();
            govGroup.setDeleted(true);
            govGroupRepository.save(govGroup);
            return ResponseEntity.status(204).body(new Data<>(true) );
        }
        return ResponseEntity.status(400).body(new Data<>(false) );
    }

    public ResponseEntity<Data<GovGroupDTO>> getById(UUID id) {

        Optional<GovGroup> byIdAndDeletedNot = govGroupRepository.findByIdAndDeletedNot(id);
        if(byIdAndDeletedNot.isPresent()){
            GovGroup govGroup = byIdAndDeletedNot.get();
            GovGroupDTO govGroupDTO = new GovGroupDTO(govGroup.getId(),govGroup.getName(),govGroup.getDescription());
            return ResponseEntity.status(200).body(new Data<>(govGroupDTO));
        }
        return ResponseEntity.status(400).body(new Data<>(false));

    }

    public ResponseEntity<Data<List<GovGroupDTO>>> getList() {
        List<GovGroup> list = govGroupRepository.findAllByDeleted(false);
        List<GovGroupDTO> govGroupDTOS = govGroupMapper.toDto(list);
        return ResponseEntity.ok(new Data<>(govGroupDTOS));

    }
}
