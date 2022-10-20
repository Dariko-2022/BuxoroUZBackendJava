package uz.dariko.collections.govGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GovGroupUpdateDTO extends GenericDTO {
    private String uzName;
    private String ruName;
    private String krName;
    private String description;
}
