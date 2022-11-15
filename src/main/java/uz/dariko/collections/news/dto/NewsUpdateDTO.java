package uz.dariko.collections.news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class NewsUpdateDTO extends GenericDTO {

    private String uzTitle;
    private String krTitle;
    private String ruTitle;

    private String uzBody;
    private String krBody;
    private String ruBody;

    private String uzDescription;
    private String krDescription;
    private String ruDescription;


    private UUID submenuID; //soha


    private List<UUID> imageIDs;

    private UUID newsSphereId;

    private boolean actual; //dolzarb

    private String sourceUrl; //manba
    private String sourceName; //manba

}
