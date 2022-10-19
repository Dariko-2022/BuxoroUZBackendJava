package uz.dariko.collections.informations.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InformationDTO extends GenericDTO {
    private String uzTitle;
    private String krTitle;
    private String ruTitle;

    private String uzBody;
    private String krBody;
    private String ruBody;

    private String uzDescription;
    private String krDescription;
    private String ruDescription;

    private List<String> generatedNames;

    private UUID infoGroupID; //soha


    private String source; //manba
}
