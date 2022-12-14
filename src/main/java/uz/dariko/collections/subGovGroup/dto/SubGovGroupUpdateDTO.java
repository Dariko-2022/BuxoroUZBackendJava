package uz.dariko.collections.subGovGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeDTO;

import java.util.List;
import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubGovGroupUpdateDTO extends GenericDTO {
    private String uzName;
    private String ruName;
    private String krName;
    private String uzDescriptionTitle;
    private String ruDescriptionTitle;
    private String krDescriptionTitle;


    private String uzTitle;
    private String ruTitle;
    private String krTitle;

    private UUID submenuId;

    private List<StateEmployeeDTO> stateEmployeeDTOS;

    private int rank;
    private boolean visible;
}
