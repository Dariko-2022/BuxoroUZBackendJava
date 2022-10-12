package uz.dariko.collections.news.dto;

import lombok.*;
import uz.dariko.base.dto.BaseDTO;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsCreateDTO implements BaseDTO {
    private String uzTitle;
    private String krTitle;
    private String ruTitle;

    private String uzBody;
    private String krBody;
    private String ruBody;


    private String sphereID; //soha

    private String govSphereID; //soha yunalishi

    private List<String> imageID;

    private boolean actual; //dolzarb

    private String source; //manba
}
