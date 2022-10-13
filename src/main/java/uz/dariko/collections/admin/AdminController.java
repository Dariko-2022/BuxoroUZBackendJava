package uz.dariko.collections.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.collections.admin.dto.AdminCreateDTO;
import uz.dariko.collections.admin.dto.AdminDTO;
import uz.dariko.collections.admin.dto.AdminUpdateDTO;
import uz.dariko.response.Data;

import java.util.List;
import java.util.UUID;

@RestController
public class AdminController extends AbstractController<AdminService> implements GenericCRUDController<AdminCreateDTO, AdminUpdateDTO, AdminDTO, UUID> {
    public AdminController(AdminService service) {
        super(service);
    }


    @Override
    public ResponseEntity<?> create(AdminCreateDTO DTO) {
        return service.create(DTO);
    }

    @Override
    public ResponseEntity<?> update(AdminUpdateDTO DTO) {
        return null;
    }

    @Override
    public ResponseEntity<Data<Boolean>> delete(UUID code) {
        return null;
    }

    @Override
    public ResponseEntity<Data<AdminDTO>> get(UUID code) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<AdminDTO>>> list() {
        return null;
    }
}
