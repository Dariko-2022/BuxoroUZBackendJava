package uz.dariko.collections.news.dto;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsCreateDTO {
    private String uzTitle;
    private String krTitle;
    private String ruTitle;

    private String uzBody;
    private String krBody;
    private String ruBody;


    private String sphereId; //soha

    private String govSphereId;

    private boolean actual; //dolzarb

    private String source; //manba
}
