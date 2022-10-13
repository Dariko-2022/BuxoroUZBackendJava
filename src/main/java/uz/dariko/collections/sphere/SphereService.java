package uz.dariko.collections.sphere;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.sphere.dto.SphereCreateDTO;

import java.util.List;


@Service
public class SphereService implements BaseService {

    private final SphereRepository sphereRepository;

    public SphereService(SphereRepository sphereRepository) {
        this.sphereRepository = sphereRepository;
    }


    public ResponseEntity<?> create(SphereCreateDTO sphereCreateDto) {
        Sphere sphere = new Sphere(
                sphereCreateDto.getUzName(),
                sphereCreateDto.getKrName(),
                sphereCreateDto.getRuName()
        );
        sphereRepository.save(sphere);
        return ResponseEntity.status(201).body("saved");
    }

    public ResponseEntity<?> getAll() {
        List<Sphere> all = sphereRepository.findAll();
        return ResponseEntity.ok(all);
    }
}
