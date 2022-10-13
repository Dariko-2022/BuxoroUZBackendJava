package uz.dariko.collections.news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    private List<String> imageIDs;

    private String sphereID; //soha

    private String govSphereID; //soha yunalishi

    private boolean actual; //dolzarb

    private boolean smm; //SMM

    private String source; //manba
}
