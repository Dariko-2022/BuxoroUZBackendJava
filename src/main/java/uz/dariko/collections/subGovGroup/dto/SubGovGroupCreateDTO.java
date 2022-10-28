package uz.dariko.collections.subGovGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;
import uz.dariko.collections.order.GovGroupOrder;
import uz.dariko.collections.order.dto.GovGroupOrderCreateDTO;

import javax.persistence.OneToMany;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubGovGroupCreateDTO implements BaseDTO {

    private String uzName;
    private String ruName;
    private String krName;

    private String uzDescription;
    private String ruDescription;
    private String krDescription;

    private UUID submenuId;

    private List<GovGroupOrderCreateDTO> orderList;

    private int rank;
    private boolean visible;

}
