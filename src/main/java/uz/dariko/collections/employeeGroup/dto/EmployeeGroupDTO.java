package uz.dariko.collections.employeeGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;

import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeGroupDTO extends GenericDTO {

    private String uzName;
    private String ruName;
    private String krName;

    private List<UUID> employeeIDs;
}
