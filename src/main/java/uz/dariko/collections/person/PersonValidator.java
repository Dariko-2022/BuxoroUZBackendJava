package uz.dariko.collections.person;

import org.springframework.stereotype.Component;
import uz.dariko.base.validator.AbstractValidator;
import uz.dariko.collections.person.dto.PersonCreateDTO;
import uz.dariko.collections.person.dto.PersonUpdateDTO;

import java.util.UUID;

@Component
public class PersonValidator extends AbstractValidator<PersonCreateDTO, PersonUpdateDTO, UUID> {
    @Override
    public void validOnCreate(PersonCreateDTO createDTO) {

    }

    @Override
    public void validOnUpdate(PersonUpdateDTO updateDTO) {

    }

    @Override
    public void validOnKey(UUID key) {

    }
}
