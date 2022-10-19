package uz.dariko.collections.informations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.file.File;
import uz.dariko.collections.govSphere.GovSphere;
import uz.dariko.collections.sphere.Sphere;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Information extends Auditable {
    private String uzTitle;
    private String krTitle;
    private String ruTitle;

    private String uzDescription;
    private String krDescription;
    private String ruDescription;

    @Type(type = "text")
    private String uzBody;
    @Type(type = "text")
    private String krBody;
    @Type(type = "text")
    private String ruBody;


    @ManyToOne
    private Sphere sphere; //soha

    @OneToMany
    private List<File> images;

    private String source; //manba
}
