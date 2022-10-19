package uz.dariko.collections.infoGroup;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.collections.infoGroup.dto.InfoGroupCreateDTO;
import uz.dariko.collections.infoGroup.dto.InfoGroupDTO;
import uz.dariko.collections.infoGroup.dto.InfoGroupUpdateDTO;

import java.util.UUID;

@RestController
@RequestMapping("infoGroup/")
public class InfoGroupController implements GenericCRUDController<InfoGroupCreateDTO, InfoGroupUpdateDTO, InfoGroupDTO, UUID> {
    @Override
    public ResponseEntity<?> create(InfoGroupCreateDTO DTO) throws Exception {
        return null;
    }

    @Override
    public ResponseEntity<?> update(InfoGroupUpdateDTO DTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(UUID uuid) {
        return null;
    }

    @Override
    public ResponseEntity<?> get(UUID uuid) {
        return null;
    }

    @Override
    public ResponseEntity<?> list() {
        return null;
    }
}
