package uz.dariko.collections.govSphere;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.govSphere.dto.GovSphereCreateDto;

@Service
public class GovSphereService implements BaseService {
    private final GovSphereRepository govSphereRepository;

    public GovSphereService(GovSphereRepository govSphereRepository) {
        this.govSphereRepository = govSphereRepository;
    }

    public ResponseEntity<?> create(GovSphereCreateDto govSphereCreateDto) {
        GovSphere govSphere = new GovSphere(
                govSphereCreateDto.getUzName(),
                govSphereCreateDto.getKrName(),
                govSphereCreateDto.getRuName()
        );

        govSphereRepository.save(govSphere);
        return ResponseEntity.status(201).body("saved");
    }
}
