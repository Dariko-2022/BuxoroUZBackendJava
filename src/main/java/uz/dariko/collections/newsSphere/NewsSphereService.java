package uz.dariko.collections.newsSphere;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.newsSphere.dto.NewsSphereCreateDTO;
import uz.dariko.collections.newsSphere.dto.NewsSphereDTO;
import uz.dariko.collections.newsSphere.dto.NewsSphereUpdateDTO;
import uz.dariko.utils.EntityGetter;

import java.util.List;

@Service
public class NewsSphereService implements BaseService {

    private final NewsSphereRepository newsSphereRepository;

    private final EntityGetter entityGetter;
    private final NewsSphereMapper mapper;


    public NewsSphereService(NewsSphereRepository newsSphereRepository, EntityGetter entityGetter, NewsSphereMapper mapper) {
        this.newsSphereRepository = newsSphereRepository;
        this.entityGetter = entityGetter;
        this.mapper = mapper;
    }

    public ResponseEntity<?> create(NewsSphereCreateDTO dto) {
        NewsSphere newsSphere = mapper.fromCreateDto(dto);
        NewsSphere save = newsSphereRepository.save(newsSphere);
        NewsSphereDTO res = mapper.toDto(save);
        return ResponseEntity.ok(res);
    }

    public ResponseEntity<?> update(NewsSphereUpdateDTO dto) {
        NewsSphere newsSphere = entityGetter.getNewsSphere(dto.getId());
        newsSphere.setUzName(dto.getUzName());
        newsSphere.setKrName(dto.getKrName());
        newsSphere.setRuName(dto.getRuName());
        NewsSphere save = newsSphereRepository.save(newsSphere);
        NewsSphereDTO res = mapper.toDto(save);
        return ResponseEntity.ok(res);
    }

    public ResponseEntity<?> getList() {
        List<NewsSphere> allByDeletedNot = newsSphereRepository.findAllByDeletedNot();
        List<NewsSphereDTO> newsSphereDTOS = mapper.toDto(allByDeletedNot);
        return ResponseEntity.ok(newsSphereDTOS);
    }
}
