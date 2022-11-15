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
public class SectorController extends AbstractController<SectorService> implements GenericCRUDController<SectorCreateDTO, SectorUpdateDTO, SectorDTO, String> {

    public SectorController(SectorService service) {
        super(service);
    }

    @Override
    public ResponseEntity<?> create(SectorCreateDTO DTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(@RequestBody SectorUpdateDTO DTO) {
        return service.update(DTO);
    }

    @Override
    public ResponseEntity<?> delete(String uuid) {
        return service.delete(uuid);
    }

    @DeleteMapping("deleteEmployee")
    public ResponseEntity<?> deleteEmployee(@PathVariable() String sectorId) {
        return service.deleteEmployee(sectorId);
    }

    @Override
    public ResponseEntity<?> get(String uuid) {
        return service.get(uuid);
    }

    @Override
    public ResponseEntity<?> list() {
        return service.get();
    }

    @RequestMapping("changeOrder")
    public ResponseEntity<?> changeOrder(BaseOrderDTO baseOrderDTO) {
        return service.changeOrder(baseOrderDTO);
    }
}
