package uz.dariko.collections.news.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.collections.govSphere.GovSphere;
import uz.dariko.collections.sphere.Sphere;

import javax.persistence.ManyToOne;
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


    private UUID sphereID; //soha

    private UUID govSphereID; //soha yunalishi

    private List<UUID> imageIDs;

    private boolean actual; //dolzarb

    private boolean smm;

    private String source; //manba

}
