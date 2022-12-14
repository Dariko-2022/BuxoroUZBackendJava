package uz.dariko.collections.link;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.AbstractService;
import uz.dariko.base.service.GenericCRUDService;
import uz.dariko.collections.link.dto.LinkCreateDTO;
import uz.dariko.collections.link.dto.LinkDTO;
import uz.dariko.collections.link.dto.LinkUpdateDTO;
import uz.dariko.utils.BaseUtils;
import uz.dariko.utils.EntityGetter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LinkService extends AbstractService<LinkRepository,LinkValidator> implements GenericCRUDService<LinkCreateDTO, LinkUpdateDTO, LinkDTO, String> {
    public LinkService(LinkRepository repository, LinkValidator validator, EntityGetter entityGetter, BaseUtils baseUtils, LinkMapper mapper) {
        super(repository, validator);
        this.entityGetter = entityGetter;
        this.baseUtils = baseUtils;
        this.mapper = mapper;
    }
    private final EntityGetter entityGetter;

    private final BaseUtils baseUtils;
    private final LinkMapper mapper;

    @Override
    public ResponseEntity<?> create(LinkCreateDTO DTO) {
        validator.validOnCreate(DTO);
        Link link = mapper.fromCreatedDTO(DTO);
        Link save = repository.save(link);
        LinkDTO linkDTO = mapper.toDTO(save);
        return ResponseEntity.ok(linkDTO);
    }

    @Override
    public ResponseEntity<?> update(LinkUpdateDTO DTO) {
        validator.validOnUpdate(DTO);
        Link link = mapper.fromUpdateDTO(DTO);
        Link save = repository.save(link);
        LinkDTO linkDTO = mapper.toDTO(save);
        return ResponseEntity.ok(linkDTO);
    }

    @Override
    public ResponseEntity<?> delete(String code) {
        UUID key = baseUtils.parseUUID(code);
        Link link = entityGetter.getLink(key);
        link.setDeleted(true);
        link.setDeletedAt(LocalDateTime.now());
        repository.save(link);
        return ResponseEntity.ok("Successfully deleted Link");
    }

    @Override
    public ResponseEntity<?> get(String code) {
        UUID key = baseUtils.parseUUID(code);
        Link link = entityGetter.getLink(key);
        return ResponseEntity.ok(mapper.toDTO(link));
    }

    @Override
    public ResponseEntity<?> list() {
        List<Link> links = repository.findAllIsDeleted(false);
        return ResponseEntity.ok(mapper.toDTO(links));
    }

    public ResponseEntity<?> listWithType(Integer code) {
        List<Link> links = repository.findAllByLinkTypeCodeIsDeleted(code, false);
        return ResponseEntity.ok(mapper.toDTO(links));
    }
}
