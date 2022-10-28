package uz.dariko.collections.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GovGroupOrderDTO implements BaseDTO {
    private int order;
    private UUID stateEmployeeId;
}
