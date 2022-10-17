package uz.dariko.collections.govSphere;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.govSphere.dto.GovSphereCreateDTO;
import uz.dariko.collections.govSphere.dto.GovSphereDTO;
import uz.dariko.collections.govSphere.dto.GovSphereUpdateDTO;
import uz.dariko.response.Data;
import uz.dariko.utils.EntityGetter;

import java.util.List;
import java.util.UUID;

@Service
public class GovSphereService implements BaseService {
    private final GovSphereRepository govSphereRepository;
    private final EntityGetter entityGetter;

    private final GovSphereMapper govSphereMapper;

    public GovSphereService(GovSphereRepository govSphereRepository, EntityGetter entityGetter, GovSphereMapper govSphereMapper) {
        this.govSphereRepository = govSphereRepository;
        this.entityGetter = entityGetter;
        this.govSphereMapper = govSphereMapper;
    }

    public ResponseEntity<?> create(GovSphereCreateDTO govSphereCreateDto) {
        GovSphere govSphere = govSphereMapper.fromCreateDto(govSphereCreateDto);

        govSphereRepository.save(govSphere);
        return ResponseEntity.status(201).body("saved");
    }

    public ResponseEntity<?> getAll() {
        List<GovSphere> all = govSphereRepository.findAllByDeleted(false);
        List<GovSphereDTO> govSphereDTOS = govSphereMapper.toDtoList(all);
        return ResponseEntity.ok(new Data<>(govSphereDTOS));
    }

    public ResponseEntity<?> update(GovSphereUpdateDTO dto){

        GovSphere govSphere = entityGetter.getGovSphere(dto.getId());
        govSphere.setUzName(dto.getUzName());
        govSphere.setKrName(dto.getKrName());
        govSphere.setRuName(dto.getRuName());
        GovSphere save = govSphereRepository.save(govSphere);
        GovSphereDTO govSphereDTO = govSphereMapper.toDto(save);
        return ResponseEntity.ok(new Data<>(govSphereDTO));

    }

    public ResponseEntity<?> get(UUID id) {
        GovSphere govSphere = entityGetter.getGovSphere(id);
        GovSphereDTO govSphereDTO = govSphereMapper.toDto(govSphere);
        return ResponseEntity.ok(new Data<>(govSphereDTO));
    }

    public ResponseEntity<?> delete(UUID id) {
        GovSphere govSphere = entityGetter.getGovSphere(id);
        govSphere.setDeleted(true);
        govSphereRepository.save(govSphere);
        return ResponseEntity.ok(new Data<>(true));

    }



}
