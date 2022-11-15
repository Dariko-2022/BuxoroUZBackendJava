package uz.dariko.collections.sector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.collections.stateEmloyee.dto.StateEmployeeDTO;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SectorDTO extends GenericDTO {

    private String uzName;
    private String krName;
    private String ruName;

    private UUID imageID;

    private StateEmployeeDTO stateEmployeeDTO;


    private String uzDescription;

    private String krDescription;

    private String ruDescription;



    private String uzSectorArea;

    private String krSectorArea;

    private String ruSectorArea;

    private int orderNumber;

}
