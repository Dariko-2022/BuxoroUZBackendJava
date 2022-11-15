package uz.dariko.collections.informations;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.AbstractService;
import uz.dariko.base.service.GenericCRUDService;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.informations.dto.InformationCreateDTO;
import uz.dariko.collections.informations.dto.InformationDTO;
import uz.dariko.collections.informations.dto.InformationUpdateDTO;
import uz.dariko.criteria.ResponsePage;
import uz.dariko.utils.BaseUtils;
import uz.dariko.utils.EntityGetter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InformationService extends AbstractService<InformationRepository,InformationValidator> implements GenericCRUDService<InformationCreateDTO, InformationUpdateDTO, InformationDTO, String> {
    public InformationService(InformationRepository repository, InformationValidator validator, InformationMapper mapper, EntityGetter entityGetter, BaseUtils baseUtils) {
        super(repository, validator);
        this.mapper = mapper;
        this.entityGetter = entityGetter;
        this.baseUtils = baseUtils;
    }
    private final InformationMapper mapper;
    private final EntityGetter entityGetter;
    private final BaseUtils baseUtils;

    @Override
    public ResponseEntity<?> create(InformationCreateDTO DTO) {
        validator.validOnCreate(DTO);
        Information information = mapper.fromCreateDto(DTO);
        Information save = repository.save(information);
        InformationDTO informationDTO = mapper.toDto(save);
        return ResponseEntity.ok(informationDTO);
    }

    @Override
    public ResponseEntity<?> update(InformationUpdateDTO DTO) {
        validator.validOnUpdate(DTO);
        Information information = mapper.fromUpdateDto(DTO);
        Information save = repository.save(information);
        InformationDTO informationDTO = mapper.toDto(save);
        return ResponseEntity.ok(informationDTO);
    }

    @Override
    public ResponseEntity<?> delete(String code) {
        UUID key = baseUtils.parseUUID(code);
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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
    public ResponseEntity<?> get(String code) {
        UUID key = baseUtils.parseUUID(code);
        validator.validOnKey(key);
        Information information = entityGetter.getInformation(key);
        return ResponseEntity.ok( mapper.toDto(information));
    }

    @Override
    public ResponseEntity<?> list() {
        return ResponseEntity.ok(mapper.toDto(repository.findAllByDeleted(false)));
    }

    public ResponseEntity<?> listByInfoGroup(String code){
        UUID infoGroupID = baseUtils.parseUUID(code);
        List<Information> informationList = repository.findAllByInfoGroup(infoGroupID, false);
        return ResponseEntity.ok(informationList);
    }

    public ResponseEntity<?> getBySubmenuId(Pageable pageable,String code) {
        UUID submenuId = baseUtils.parseUUID(code);
        int offset = pageable.getPageSize()* pageable.getPageNumber();
        int size = pageable.getPageSize();
        Page<Information> page = repository.findBySubmenuIdAndIsDeleted(submenuId,pageable,false);
        List<Information> allBySubmenuIdAndDeletedNot = repository.findAllBySubmenuIdAndDeletedNot(submenuId, size, offset,false);
        List<InformationDTO> informationDTOS = mapper.toDto(allBySubmenuIdAndDeletedNot);
        ResponsePage<InformationDTO> responsePage = baseUtils.toResponsePage(page, informationDTOS);
        return ResponseEntity.ok(responsePage);
    }
}
