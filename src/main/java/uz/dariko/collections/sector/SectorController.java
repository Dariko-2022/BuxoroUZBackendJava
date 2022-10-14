package uz.dariko.collections.sector;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.collections.sector.dto.SectorCreateDTO;
import uz.dariko.collections.sector.dto.SectorDTO;
import uz.dariko.collections.sector.dto.SectorDtoForHome;
import uz.dariko.collections.sector.dto.SectorUpdateDTO;
import uz.dariko.response.Data;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("sector")
public class SectorController extends AbstractController<SectorService> implements GenericCRUDController<SectorCreateDTO, SectorUpdateDTO, SectorDTO, UUID> {

    public SectorController(SectorService service) {
        super(service);
    }

    @Override
    @PostMapping
    public ResponseEntity<?> create(SectorCreateDTO DTO) throws Exception {
        return service.create(DTO);
    }

    @Override
    @PutMapping
    public ResponseEntity<?> update(SectorUpdateDTO DTO) {
        return null;
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Data<Boolean>> delete(UUID uuid) {
        return null;
    }

    @Override
    @GetMapping
    public ResponseEntity<Data<SectorDTO>> get(UUID uuid) {
        return null;
    }

    @Override
    @GetMapping("getAll")
    public ResponseEntity<Data<List<SectorDTO>>> list() {
        return null;
    }

    @GetMapping("getAllById")
    public ResponseEntity<Data<List<SectorDtoForHome>>> listById(String regionId) {
        return service.getById(regionId);
    }
}
