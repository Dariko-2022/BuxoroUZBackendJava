package uz.dariko.collections.infoGroup;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.collections.infoGroup.dto.InfoGroupCreateDTO;
import uz.dariko.collections.infoGroup.dto.InfoGroupDTO;
import uz.dariko.collections.infoGroup.dto.InfoGroupUpdateDTO;

import java.util.UUID;

@RestController
@RequestMapping("infoGroup")
public class InfoGroupController extends AbstractController<InfoGroupService> implements GenericCRUDController<InfoGroupCreateDTO, InfoGroupUpdateDTO, InfoGroupDTO, UUID> {
    public InfoGroupController(InfoGroupService service) {
        super(service);
    }

    @Override
    public ResponseEntity<?> create(InfoGroupCreateDTO DTO) {
        return service.create(DTO);
    }

    @Override
    public ResponseEntity<?> update(InfoGroupUpdateDTO DTO) {
        return service.update(DTO);
    }

    @Override
    public ResponseEntity<?> delete(
            @PathVariable("code") UUID uuid) {
        return service.delete(uuid);
    }

    @Override
    public ResponseEntity<?> get(UUID uuid) {
        return service.get(uuid);
    }

    @Override
    public ResponseEntity<?> list() {
        return service.getAll();
    }
}
