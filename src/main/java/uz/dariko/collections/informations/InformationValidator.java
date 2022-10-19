package uz.dariko.collections.informations;

import org.springframework.stereotype.Component;
import uz.dariko.base.validator.AbstractValidator;
import uz.dariko.collections.informations.dto.InformationCreateDTO;
import uz.dariko.collections.informations.dto.InformationDTO;
import uz.dariko.collections.informations.dto.InformationUpdateDTO;

import java.util.UUID;

@Component
public class InformationValidator extends AbstractValidator<InformationCreateDTO, InformationUpdateDTO, UUID> {
    @Override
    public void validOnCreate(InformationCreateDTO createDTO) {

    }

    @Override
    public void validOnUpdate(InformationUpdateDTO updateDTO) {

    }

    @Override
    public void validOnKey(UUID key) {

    }


}
