package uz.dariko.collections.submenu.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubmenuUpdateDTO extends GenericDTO {
    private String uzName;
    private String krName;
    private String ruName;
    private UUID menuId;

    private String url;

    private boolean visible;

}
