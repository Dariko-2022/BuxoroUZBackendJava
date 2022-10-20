package uz.dariko.collections.govGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GovGroupCreateDTO implements BaseDTO {

    private String uzName;
    private String ruName;
    private String krName;

    private String description;

}
