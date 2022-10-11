package uz.dariko.base.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class GenericDTO implements BaseDTO {
    private UUID id;
}
