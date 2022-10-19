package uz.dariko.collections.informations;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.service.AbstractService;
import uz.dariko.base.service.GenericCRUDService;
import uz.dariko.collections.informations.dto.InformationCreateDTO;
import uz.dariko.collections.informations.dto.InformationDTO;
import uz.dariko.collections.informations.dto.InformationUpdateDTO;

import java.util.UUID;

@Service
public class InformationService extends AbstractService<InformationRepository,InformationValidator> implements GenericCRUDService<InformationCreateDTO, InformationUpdateDTO, InformationDTO, UUID> {
    public InformationService(InformationRepository repository, InformationValidator validator) {
        super(repository, validator);
    }

    @Override
    public ResponseEntity<?> create(InformationCreateDTO DTO) {
        return null;
    }

    @Override
    public ResponseEntity<?> update(InformationUpdateDTO DTO) {
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
    public ResponseEntity<?> list() {
        return null;
    }
}
