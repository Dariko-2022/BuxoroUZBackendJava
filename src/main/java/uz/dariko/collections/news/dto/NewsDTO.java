package uz.dariko.collections.news.dto;

import uz.dariko.base.dto.GenericDTO;

import java.util.List;

public class NewsDTO extends GenericDTO {
    private String uzTitle;
    private String krTitle;
    private String ruTitle;

    private String uzBody;
    private String krBody;
    private String ruBody;


    private String sphereID; //soha

    private String govSphereID; //soha yunalishi

    private List<String> imageIDs;

    private int countView;

    private boolean actual; //dolzarb

    private String source; //manba
}
