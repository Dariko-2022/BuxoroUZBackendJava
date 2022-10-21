package uz.dariko.collections.sector;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.base.dto.BaseOrderDTO;
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
    public ResponseEntity<?> create(SectorCreateDTO DTO) {
        return service.create(DTO);
    }

    @Override
    public ResponseEntity<?> update(SectorUpdateDTO DTO) {
        return service.update(DTO);
    }

    @Override
    public ResponseEntity<Data<Boolean>> delete(UUID uuid) {
        return service.delete(uuid);
    }

    @Override
    public ResponseEntity<Data<SectorDTO>> get(UUID uuid) {
        return service.get(uuid);
    }

    @Override
    public ResponseEntity<Data<List<SectorDTO>>> list() {
        return service.get();
    }

    @RequestMapping("changeOrder")
    public ResponseEntity<Data<List<SectorDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
        return service.changeOrder(baseOrderDTO);
    }
}
