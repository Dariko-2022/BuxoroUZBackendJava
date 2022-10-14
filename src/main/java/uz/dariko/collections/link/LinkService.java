package uz.dariko.collections.link;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.AbstractService;
import uz.dariko.base.service.GenericCRUDService;
import uz.dariko.collections.file.File;
import uz.dariko.collections.link.dto.LinkCreateDTO;
import uz.dariko.collections.link.dto.LinkDTO;
import uz.dariko.collections.link.dto.LinkUpdateDTO;
import uz.dariko.response.Data;
import uz.dariko.utils.BaseUtils;
import uz.dariko.utils.EntityGetter;

import java.util.List;
import java.util.UUID;

@Service
public class LinkService extends AbstractService<LinkRepository,LinkValidator> implements GenericCRUDService<LinkCreateDTO, LinkUpdateDTO, LinkDTO, UUID> {
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
        UUID uuid = baseUtils.parseUUID(DTO.getImageID());
        File image = entityGetter.getFile(uuid);
        Link link=new Link(DTO.getUzName(),DTO.getKrName(),DTO.getRuName(),DTO.getLinkTypeCode(),image,DTO.getUrl());

        repository.save(link);
        return ResponseEntity.ok("Successfully created link");
    }

    @Override
    public ResponseEntity<?> update(LinkUpdateDTO DTO) {
        validator.validOnUpdate(DTO);
        UUID uuid = baseUtils.parseUUID(DTO.getImageID());
        File image = entityGetter.getFile(uuid);
        Link link1 = entityGetter.getLink(DTO.getId());
        link1.setUzName(DTO.getUzName());
        link1.setKrName(DTO.getKrName());
        link1.setRuName(DTO.getRuName());
        link1.setLinkTypeCode(DTO.getLinkTypeCode());
        link1.setImage(image);
        link1.setUrl(DTO.getUrl());
        repository.save(link1);
        return ResponseEntity.ok("Successfully Updated Link");
    }

    @Override
    public ResponseEntity<?> delete(UUID key) {
        Link link = entityGetter.getLink(key);
        link.setDeleted(true);
        repository.save(link);
        return ResponseEntity.ok("Successfully deleted Link");
    }

    @Override
    public ResponseEntity<?> get(UUID key) {
        Link link = entityGetter.getLink(key);
        return ResponseEntity.ok(mapper.toLinkDTO(link));
    }

    @Override
    public ResponseEntity<?> list() {
        List<Link> links = repository.findAllIsDeleted(false);
        return ResponseEntity.ok(mapper.toLinkDTO(links));
    }

    public ResponseEntity<?> listWithType(Integer code) {
        List<Link> links = repository.findAllByLinkTypeCodeIsDeleted(code, false);
        return ResponseEntity.ok(mapper.toLinkDTO(links));
    }
}
