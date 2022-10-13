package uz.dariko.collections.admin;

import org.springframework.stereotype.Component;
import uz.dariko.base.validator.AbstractValidator;
import uz.dariko.base.validator.BaseValidator;
import uz.dariko.collections.admin.dto.AdminCreateDTO;
import uz.dariko.collections.admin.dto.AdminUpdateDTO;
import uz.dariko.exception.exceptions.BadRequestException;

import java.util.UUID;

@Component
public class AdminValidator extends AbstractValidator<AdminCreateDTO, AdminUpdateDTO, UUID> {
    @Override
    public void validOnCreate(AdminCreateDTO createDTO) {

        if (createDTO.getEmail()== null) {
            throw new BadRequestException("Email null bo'lishi mumkin emas");
        }
        if (createDTO.getPhoneNumber()== null || createDTO.getPhoneNumber().length()<7) {
            throw new BadRequestException("Telefon nomerni to'g'ri kirgizing");
        }
        if (createDTO.getUsername()== null) {
            throw new BadRequestException("Username null bo'lishi mumkin emas");
        }


    }

    @Override
    public void validOnUpdate(AdminUpdateDTO updateDTO) {

    }

    @Override
    public void validOnKey(UUID key) {

    }
}
