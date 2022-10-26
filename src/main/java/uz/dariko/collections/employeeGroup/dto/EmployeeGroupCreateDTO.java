package uz.dariko.collections.employeeGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;

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
    private List<UUID> employeeList;

    private UUID menuId;
}
