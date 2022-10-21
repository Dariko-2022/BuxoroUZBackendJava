package uz.dariko.collections.infoGroup.dto;

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
public class InfoGroupDTO extends GenericDTO {
    private String uzName;
    private String krName;
    private String ruName;

    private String type;

    private UUID menuId;

    private int rank;
    private boolean visible;
}
