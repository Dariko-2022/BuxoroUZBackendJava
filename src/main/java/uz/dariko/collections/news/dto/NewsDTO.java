package uz.dariko.collections.news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO extends GenericDTO {
    private String uzTitle;
    private String krTitle;
    private String ruTitle;

    private String uzDescription;
    private String krDescription;
    private String ruDescription;

    private String uzBody;
    private String krBody;
    private String ruBody;


    private UUID submenuID; //soha


    private List<UUID> generatedNames;
    private String generatedName;
    private LocalDateTime createdDate;

    private int countView;

    private UUID newsSphereId;

    private boolean actual; //dolzarb

    private String source; //manba
}
