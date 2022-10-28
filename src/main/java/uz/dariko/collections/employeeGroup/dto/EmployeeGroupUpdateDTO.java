package uz.dariko.collections.employeeGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.base.dto.OrderDTO;
import uz.dariko.collections.order.dto.GovGroupOrderCreateDTO;
import uz.dariko.collections.order.dto.GovGroupOrderDTO;
import uz.dariko.collections.order.dto.GovGroupOrderUpdateDTO;

import java.util.List;
import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeGroupUpdateDTO extends GenericDTO {

    private String uzName;
    private String ruName;
    private String krName;

    private UUID submenuId;

    private List<GovGroupOrderUpdateDTO> orderList;
}
