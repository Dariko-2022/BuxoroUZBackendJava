package uz.dariko.collections.footerElements;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.footerElements.dto.FooterElementCreateDTO;
import uz.dariko.utils.BaseUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class FooterElementService implements BaseService {
    private final FooterElementRepository footerElementRepository;

    private final BaseUtils baseUtils;
    private final FooterElementMapper footerElementMapper;

    public FooterElementService(FooterElementRepository footerElementRepository, BaseUtils baseUtils, FooterElementMapper footerElementMapper) {
        this.footerElementRepository = footerElementRepository;
        this.baseUtils = baseUtils;
        this.footerElementMapper = footerElementMapper;
    }

    public ResponseEntity<?> create(FooterElementCreateDTO dto) {
        Optional<FooterElement> byTypeId = footerElementRepository.findByTypeId(dto.getType());
        FooterElement footerElement0 = byTypeId.orElseGet(FooterElement::new);
        FooterElement footerElement = footerElementMapper.fromCreateDTO(footerElement0,dto);
//        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if(footerElement.getId() != null) {
//            footerElement.setUpdatedAt(LocalDateTime.now());
//            footerElement.setUpdatedBy(sessionUser.getId());
//        }else {
//            footerElement.setCreatedAt(LocalDateTime.now());
//            footerElement.setCreatedBy(sessionUser.getId());
//        }
        FooterElement save = footerElementRepository.save(footerElement);
        return ResponseEntity.ok(footerElementMapper.toDTO(save));
    }

    public ResponseEntity<?> getByType(String code) {
        int i = baseUtils.parseInt(code);
        Optional<FooterElement> byTypeId = footerElementRepository.findByTypeId(i);
        if(byTypeId.isPresent()) {
            return ResponseEntity.ok(footerElementMapper.toDTO(byTypeId.get()));
        }else {
            return ResponseEntity.ok("Empty");
        }
    }

    public ResponseEntity<?> getList() {
        List<FooterElement> all = footerElementRepository.findAll();
        return ResponseEntity.ok(footerElementMapper.toDTO(all));
    }

}
