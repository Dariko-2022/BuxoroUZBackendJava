package uz.dariko.collections.subGovGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.collections.order.dto.GovGroupOrderDTO;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubGovGroupDTO extends GenericDTO {
    private String uzName;
    private String ruName;
    private String krName;
    private String uzDescription;
    private String ruDescription;
    private String krDescription;

    private String type;

    private UUID submenuId;

    private List<GovGroupOrderDTO> orderList;

    private int rank;
    private boolean visible;

}
