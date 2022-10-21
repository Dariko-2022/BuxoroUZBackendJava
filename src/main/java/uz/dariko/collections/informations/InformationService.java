package uz.dariko.collections.informations;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.AbstractService;
import uz.dariko.base.service.GenericCRUDService;
import uz.dariko.collections.informations.dto.InformationCreateDTO;
import uz.dariko.collections.informations.dto.InformationDTO;
import uz.dariko.collections.informations.dto.InformationUpdateDTO;
import uz.dariko.utils.EntityGetter;
import uz.dariko.utils.dtos.SessionUser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InformationService extends AbstractService<InformationRepository,InformationValidator> implements GenericCRUDService<InformationCreateDTO, InformationUpdateDTO, InformationDTO, UUID> {
    public InformationService(InformationRepository repository, InformationValidator validator, InformationMapper mapper, EntityGetter entityGetter) {
        super(repository, validator);
        this.mapper = mapper;
        this.entityGetter = entityGetter;
    }
    private final InformationMapper mapper;
    private final EntityGetter entityGetter;

    @Override
    public ResponseEntity<?> create(InformationCreateDTO DTO) {
        validator.validOnCreate(DTO);
        Information information = mapper.fromCreateDto(DTO);
        repository.save(information);
        return ResponseEntity.ok("Successfully Created Information");
    }

    @Override
    public ResponseEntity<?> update(InformationUpdateDTO DTO) {
        validator.validOnUpdate(DTO);
        Information information = mapper.fromUpdateDto(DTO);
        repository.save(information);
        return ResponseEntity.ok("Successfully Updated Information ");
    }

    @Override
    public ResponseEntity<?> delete(UUID key) {
        SessionUser sessionUser= (SessionUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        validator.validOnKey(key);
        Information information = entityGetter.getInformation(key);
        information.setDeleted(true);
        information.setDeletedAt(LocalDateTime.now());
        information.setUpdatedBy(sessionUser.getId());
        information.setUpdatedAt(LocalDateTime.now());
        repository.save(information);
        return ResponseEntity.ok("Successfully Deleted");
    }

    @Override
    public ResponseEntity<?> get(UUID key) {
        validator.validOnKey(key);
        Information information = entityGetter.getInformation(key);
        return ResponseEntity.ok( mapper.toDto(information));
    }

    @Override
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(mapper.toDto(repository.findAllByDeleted(false)));
    }

    public ResponseEntity<?> listByInfoGroup(UUID infoGroupID){
        List<Information> informationList = repository.findAllByInfoGroup(infoGroupID, false);
        return ResponseEntity.ok(informationList);
    }
}
