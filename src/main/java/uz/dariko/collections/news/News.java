package uz.dariko.collections.news;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.govSphere.GovSphere;
import uz.dariko.collections.sphere.Sphere;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class News extends Auditable {
    private String uzTitle;
    private String krTitle;
    private String ruTitle;

    private String uzBody;
    private String krBody;
    private String ruBody;


    @ManyToOne
    private Sphere sphere; //soha

    @ManyToOne
    private GovSphere govSphere; //soha yunalishi

    private boolean actual; //dolzarb

    private int countView=0; //kurishlar soni

    private String source; //manba

}
