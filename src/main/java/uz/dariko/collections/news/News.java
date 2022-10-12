package uz.dariko.collections.news;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.sphere.Sphere;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Set;

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

    private boolean actual; //dolzarb

    private int countView=0; //kurishlar soni

    private String source; //manba

}
