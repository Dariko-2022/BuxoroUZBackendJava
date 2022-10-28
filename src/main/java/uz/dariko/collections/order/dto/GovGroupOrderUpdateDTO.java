package uz.dariko.collections.order.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GovGroupOrderUpdateDTO extends GenericDTO {
    private int order;
    private UUID stateEmployeeId;
}
