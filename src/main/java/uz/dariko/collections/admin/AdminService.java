package uz.dariko.collections.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.AbstractService;
import uz.dariko.base.service.GenericCRUDService;
import uz.dariko.collections.admin.dto.AdminCreateDTO;
import uz.dariko.collections.admin.dto.AdminDTO;
import uz.dariko.collections.admin.dto.AdminUpdateDTO;
import uz.dariko.collections.file.File;
import uz.dariko.response.Data;
import uz.dariko.utils.BaseUtils;
import uz.dariko.utils.EntityGetter;

import java.util.List;
import java.util.UUID;

@Service
public class AdminService extends AbstractService<AdminRepository,AdminValidator> implements GenericCRUDService<AdminCreateDTO, AdminUpdateDTO, AdminDTO, UUID> {
    public AdminService(AdminRepository repository, AdminValidator validator, EntityGetter entityGetter, BaseUtils baseUtils) {
        super(repository, validator);
        this.entityGetter = entityGetter;
        this.baseUtils = baseUtils;
    }

    private final EntityGetter entityGetter;
    private final BaseUtils baseUtils;


    @Override
    public ResponseEntity<?> create(AdminCreateDTO DTO) {
        validator.validOnCreate(DTO);
        UUID imageID = baseUtils.parseUUID(DTO.getImageID());
        File image = entityGetter.getFile(imageID);

        Admin admin=new Admin(DTO.getUsername(),DTO.getPassword(),image,DTO.getFirstName(), DTO.getLastName(), DTO.getEmail(), DTO.getPhoneNumber(), DTO.getIsSuperAdmin());
        Admin save = repository.save(admin);
        return ResponseEntity.ok(new Data<>(save));
    }

    @Override
    public ResponseEntity<?> update(AdminUpdateDTO DTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(UUID key) {
        return null;
    }

    @Override
    public ResponseEntity<?> get(UUID key) {
        return null;
    }

    @Override
    public ResponseEntity<Data<List<AdminDTO>>> list() {
        return null;
    }
}
