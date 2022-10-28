package uz.dariko.collections.employeeGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;
import uz.dariko.collections.order.dto.GovGroupOrderCreateDTO;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeGroupCreateDTO implements BaseDTO {

    private String uzName;
    private String ruName;
    private String krName;
    private List<GovGroupOrderCreateDTO> orderList;

    private UUID submenuId;
}
