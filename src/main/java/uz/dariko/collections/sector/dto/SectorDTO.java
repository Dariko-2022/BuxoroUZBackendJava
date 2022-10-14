package uz.dariko.collections.sector.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SectorDTO extends GenericDTO {

    private String uzName;
    private String krName;
    private String ruName;

    private String generatedName;

    private UUID sectorLeaderId;

    private String description;

    private String sectorArea;

}
