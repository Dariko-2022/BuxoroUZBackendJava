package uz.dariko.collections.subGovGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubGovGroupNameUpdateDTO extends GenericDTO {

    private String uzName;
    private String ruName;
    private String krName;
    private String uzDescriptionTitle;
    private String ruDescriptionTitle;
    private String krDescriptionTitle;

    private String uzTitle;
    private String ruTitle;
    private String krTitle;
}
