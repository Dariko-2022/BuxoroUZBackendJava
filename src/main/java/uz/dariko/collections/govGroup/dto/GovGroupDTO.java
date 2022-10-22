package uz.dariko.collections.govGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.collections.menu.Menu;
import uz.dariko.collections.subGovGroup.dto.SubGovGroupDTO;

import javax.persistence.ManyToOne;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GovGroupDTO extends GenericDTO {
    private UUID id;
    private String uzName;
    private String ruName;
    private String krName;

    private String type;

    private UUID menuId;

    private List<SubGovGroupDTO> subGovGroupDTOList;

    private int rank;
    private boolean visible;

}
