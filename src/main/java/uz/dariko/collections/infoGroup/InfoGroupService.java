package uz.dariko.collections.infoGroup;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.BaseService;

@Service
public class InfoGroupService implements BaseService {

    private final InfoGroupRepository infoGroupRepository;

    private final InfoGroupMapper infoGroupMapper;



    public InfoGroupService(InfoGroupRepository infoGroupRepository, InfoGroupMapper infoGroupMapper) {
        this.infoGroupRepository = infoGroupRepository;
        this.infoGroupMapper = infoGroupMapper;
    }

//    public ResponseEntity<?> create()
}
