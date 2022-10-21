package uz.dariko.base.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.collections.menu.Menu;

import javax.persistence.ManyToOne;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubMenuAdminDTO extends GenericDTO {

    private String uzName;
    private String ruName;
    private String krName;

    private String uzDescription;
    private String ruDescription;
    private String krDescription;

    private UUID menuId;

    private int rank;
}
