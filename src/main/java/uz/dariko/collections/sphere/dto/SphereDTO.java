package uz.dariko.collections.sphere.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.collections.menu.Menu;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SphereDTO extends GenericDTO {
    private String uzName;
    private String krName;
    private String ruName;

    private UUID menuId;

    private String type;
    private int rank;
    private boolean visible;
}
