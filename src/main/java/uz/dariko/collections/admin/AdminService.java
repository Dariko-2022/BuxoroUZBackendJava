package uz.dariko.collections.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    public AdminService(AdminRepository repository, AdminValidator validator, EntityGetter entityGetter, BaseUtils baseUtils, AdminMapper adminMapper, PasswordEncoder passwordEncoder) {
        super(repository, validator);
        this.entityGetter = entityGetter;
        this.baseUtils = baseUtils;
        this.mapper = adminMapper;
        this.passwordEncoder = passwordEncoder;
    }

    private final EntityGetter entityGetter;
    private final BaseUtils baseUtils;

    private final AdminMapper mapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public ResponseEntity<?> create(AdminCreateDTO DTO) {
        validator.validOnCreate(DTO);
        File image = entityGetter.getFile(DTO.getImageID());
        Admin admin=new Admin(DTO.getUsername(),passwordEncoder.encode(DTO.getPassword()),image,DTO.getFirstName(), DTO.getLastName(), DTO.getEmail(), DTO.getPhoneNumber(), DTO.getIsSuperAdmin());
        Admin save = repository.save(admin);
        return ResponseEntity.ok(new Data<>(save));
    }

    @Override
    public ResponseEntity<?> update(AdminUpdateDTO DTO) {
        validator.validOnUpdate(DTO);
        File image = entityGetter.getFile(DTO.getImageID());
        Admin admin = entityGetter.getAdmin(DTO.getUsername());
        admin.setUsername(DTO.getUsername());
        admin.setPassword(DTO.getPassword());
        admin.setImage(image);
        admin.setFirstName(DTO.getFirstName());
        admin.setLastName(DTO.getLastName());
        admin.setEmail(DTO.getEmail());
        admin.setPhoneNumber(DTO.getPhoneNumber());
        repository.save(admin);
        return ResponseEntity.ok("Successfully updated Admin");
    }

    @Override
    public ResponseEntity<?> delete(UUID key) {
        Admin admin = entityGetter.getAdmin(key);
        admin.setDeleted(true);
        return ResponseEntity.ok("Successfully deleted Admin");
    }

    @Override
    public ResponseEntity<?> get(UUID key) {
        Admin admin = entityGetter.getAdmin(key);
        return ResponseEntity.ok(mapper.toDTO(admin));
    }

    @Override
    public ResponseEntity<?> list() {
        List<Admin> allByDeleted = repository.findAllByDeleted(false);
        return ResponseEntity.ok(mapper.toDTO(allByDeleted));
    }
}
