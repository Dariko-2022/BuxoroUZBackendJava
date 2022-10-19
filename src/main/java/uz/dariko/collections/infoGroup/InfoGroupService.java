package uz.dariko.collections.infoGroup;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.infoGroup.dto.InfoGroupCreateDTO;
import uz.dariko.collections.infoGroup.dto.InfoGroupDTO;
import uz.dariko.collections.infoGroup.dto.InfoGroupUpdateDTO;
import uz.dariko.utils.EntityGetter;

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

}
