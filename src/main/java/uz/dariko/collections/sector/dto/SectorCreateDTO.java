package uz.dariko.collections.sector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SectorCreateDTO implements BaseDTO {

    private String uzName;
    private String krName;
    private String ruName;

    private String fileId;

    private String stateEmployeeId;

    private String regionId;

    private String description;

    private String sectorArea;

}
