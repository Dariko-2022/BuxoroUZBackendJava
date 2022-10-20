package uz.dariko.collections.stateEmloyee;

import org.springframework.stereotype.Component;
import uz.dariko.base.validator.AbstractValidator;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeCreateDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeUpdateDTO;

import java.util.UUID;

@Component
public class StateEmployeeValidator extends AbstractValidator<StateEmployeeCreateDTO, StateEmployeeUpdateDTO, UUID> {
    @Override
    public void validOnCreate(StateEmployeeCreateDTO createDTO) {

    }

    @Override
    public void validOnUpdate(StateEmployeeUpdateDTO updateDTO) {

    }

    @Override
    public void validOnKey(UUID key) {

    }
}
