package uz.dariko.base.validator;

import uz.dariko.base.dto.BaseDTO;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.base.dto.OrderDTO;
import uz.dariko.exception.exceptions.BadRequestException;
import uz.dariko.exception.exceptions.InvalidValidationException;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractValidator<CD extends BaseDTO, UD extends GenericDTO, K extends Serializable>
        implements BaseValidator {

    public abstract void validOnCreate(CD createDTO);

    public abstract void validOnUpdate(UD updateDTO);

    public abstract void validOnKey(K key);

    public void checkPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            throw new InvalidValidationException("Telefon nomer null/bo'sh bo'lishi mumkun emas");
        }
//        if (phoneNumber.length() != 13 || !phoneNumber.startsWith("+998")) {
//            throw new InvalidValidationException("Telefon nomer +998yyxxxxxxx formatida bo'lishi kerak");
//        }
    }


    public void checkForPositiveNumber(Integer number, String message) {
        if (number < 1) {
            throw new InvalidValidationException(message);
        }
    }


    public void validOnBaseOrderDTO(List<OrderDTO> orderDTOS) {
        if (orderDTOS == null) {
            throw new BadRequestException("Tartibni o'zgartirshdagi list null bo'lishi mumkin emas");
        }
    }
}



