package uz.dariko.collections.sphere.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;
import uz.dariko.collections.menu.Menu;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SphereCreateDTO implements BaseDTO {

    private String uzName;
    private String krName;
    private String ruName;

    private UUID menuId;

    private int rank;
    private boolean visible;

}
