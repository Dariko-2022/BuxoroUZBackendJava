package uz.dariko.collections.link;


import org.springframework.stereotype.Component;
import uz.dariko.base.validator.AbstractValidator;
import uz.dariko.collections.link.dto.LinkCreateDTO;
import uz.dariko.collections.link.dto.LinkUpdateDTO;

import java.util.UUID;
@Component
public class LinkValidator extends AbstractValidator<LinkCreateDTO, LinkUpdateDTO, UUID> {
    @Override
    public void validOnCreate(LinkCreateDTO createDTO) {

    }

    @Override
    public void validOnUpdate(LinkUpdateDTO updateDTO) {

    }

    @Override
    public void validOnKey(UUID key) {

    }
}
