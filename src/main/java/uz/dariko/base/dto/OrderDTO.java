package uz.dariko.base.dto;

import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Getter
@Setter
public class OrderDTO {
    private UUID id;
    private Integer order;
}
