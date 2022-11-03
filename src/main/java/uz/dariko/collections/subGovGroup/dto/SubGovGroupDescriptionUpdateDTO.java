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
public class SubGovGroupDescriptionUpdateDTO extends GenericDTO {

    private String uzDescription;
    private String ruDescription;
    private String krDescription;

}
