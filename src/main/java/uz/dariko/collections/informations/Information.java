package uz.dariko.collections.informations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.file.File;
import uz.dariko.collections.submenu.Submenu;

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

    @Type(type = "text")
    private String uzDescription;
    @Type(type = "text")
    private String krDescription;
    @Type(type = "text")
    private String ruDescription;

    @Type(type = "text")
    private String uzBody;
    @Type(type = "text")
    private String krBody;
    @Type(type = "text")
    private String ruBody;


    @ManyToOne
    private Submenu submenu;

    @OneToMany
    private List<File> images;

    private String source; //manba
}
