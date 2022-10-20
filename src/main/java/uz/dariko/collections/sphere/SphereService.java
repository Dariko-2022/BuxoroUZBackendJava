package uz.dariko.collections.sphere;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.dto.BaseOrderDTO;
import uz.dariko.base.dto.OrderDTO;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.sphere.dto.SphereCreateDTO;
import uz.dariko.collections.sphere.dto.SphereDTO;
import uz.dariko.collections.sphere.dto.SphereUpdateDTO;
import uz.dariko.response.Data;
import uz.dariko.utils.EntityGetter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class SphereService implements BaseService{

    private final SphereRepository sphereRepository;

    private final SphereMapper sphereMapper;

    private final EntityGetter entityGetter;

    public SphereService(SphereRepository sphereRepository, SphereMapper sphereMapper, EntityGetter entityGetter) {
        this.sphereRepository = sphereRepository;
        this.sphereMapper = sphereMapper;
        this.entityGetter = entityGetter;
    }

    public ResponseEntity<?> create(SphereCreateDTO sphereCreateDto) {
        Sphere sphere = sphereMapper.fromCreateDto(sphereCreateDto);
        sphereRepository.save(sphere);
        return ResponseEntity.status(201).body("saved");
    }

    public ResponseEntity<?> getAll() {
        Optional<List<Sphere>> allByDeleted = sphereRepository.findAllByDeleted(false);
        if(allByDeleted.isPresent()) {
            return ResponseEntity.ok(allByDeleted.get());
        }
        return ResponseEntity.ok("Not Found");

    }

    public ResponseEntity<?> update(SphereUpdateDTO dto) {

        Sphere sphere = entityGetter.getSphere(dto.getId());

        sphere.setUzName(dto.getUzName());
        sphere.setKrName(dto.getKrName());
        sphere.setRuName(dto.getRuName());
        sphereRepository.save(sphere);
        return ResponseEntity.status(204).body(sphere);

    }

    public ResponseEntity<?> delete(UUID id) {
        Optional<Sphere> byId = sphereRepository.findById(id);
        if(byId.isPresent()){
            Sphere sphere = byId.get();
            sphere.setDeleted(true);
            sphereRepository.save(sphere);
            return ResponseEntity.status(204).body("Deleted");
        }
        return ResponseEntity.status(404).body("Not Found");
    }

    public ResponseEntity<?> get(UUID id) {
        Optional<Sphere> byId = sphereRepository.findById(id);
        return byId.isPresent()? ResponseEntity.ok(byId.get()) : ResponseEntity.status(404).body("Not Found");
    }

    public ResponseEntity<Data<List<SphereDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
        List<OrderDTO> orderDTOS = baseOrderDTO.getOrders();
        List<Sphere> entities = new ArrayList<>();
        for (OrderDTO order : orderDTOS) {
            Sphere entity = sphereRepository.setOrOrderNumber(order.getId(), order.getOrder());
            entities.add(entity);
        }
        return new ResponseEntity<>(new Data<>(sphereMapper.toDto(entities)), HttpStatus.OK);
    }
}
