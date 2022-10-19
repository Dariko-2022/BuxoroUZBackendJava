package uz.dariko.collections.informations.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InformationCreateDTO implements BaseDTO {
    private String uzTitle;
    private String krTitle;
    private String ruTitle;

    private String uzBody;
    private String krBody;
    private String ruBody;

    private String uzDescription;
    private String krDescription;
    private String ruDescription;

    private List<UUID> imageIDs;

    private UUID infoGroupID; //yo'nalishi


    private String source; //manba
}
