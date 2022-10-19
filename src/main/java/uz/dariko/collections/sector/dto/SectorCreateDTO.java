package uz.dariko.collections.sector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;

import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SectorCreateDTO implements BaseDTO {

    private String uzName;
    private String krName;
    private String ruName;

    private UUID fileId;

    private UUID stateEmployeeId;

    private UUID regionId;


    private String description;

    private String sectorArea;


}
