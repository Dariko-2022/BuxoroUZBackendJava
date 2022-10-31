package uz.dariko.collections.subGovGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;

import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubGovGroupCreateDTO implements BaseDTO {

    private String uzName;
    private String ruName;
    private String krName;

    private String uzDescriptionTitle;
    private String ruDescriptionTitle;
    private String krDescriptionTitle;

//    private String uzDescription;
//    private String ruDescription;
//    private String krDescription;

    private String uzTitle;
    private String ruTitle;
    private String krTitle;

    private UUID submenuId;



}
