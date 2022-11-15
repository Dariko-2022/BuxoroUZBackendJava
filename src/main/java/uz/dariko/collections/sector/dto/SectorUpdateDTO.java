package uz.dariko.collections.sector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeCreateDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeUpdateDTO;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SectorUpdateDTO extends GenericDTO {

    private String uzName;
    private String krName;
    private String ruName;

    private UUID fileId;

//    private UUID stateEmployeeId;

    private String uzDescription;
    private String krDescription;
    private String ruDescription;

    private String uzSectorArea;
    private String krSectorArea;
    private String ruSectorArea;

    //employee

    private StateEmployeeUpdateDTO stateEmployeeUpdateDTO;
}
