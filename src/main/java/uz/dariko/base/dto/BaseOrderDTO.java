package uz.dariko.base.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BaseOrderDTO implements BaseDTO {
    private List<OrderDTO> orders;
}
