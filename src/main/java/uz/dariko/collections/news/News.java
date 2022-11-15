package uz.dariko.collections.news;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.file.File;
import uz.dariko.collections.newsSphere.NewsSphere;
import uz.dariko.collections.submenu.Submenu;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class News extends Auditable {
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

    @ManyToOne(fetch = FetchType.EAGER)
    private NewsSphere newsSphere;

    @ManyToOne
    private Submenu submenu; //soha

    @OneToMany
    private List<File> images;

    private boolean actual; //dolzarb

    private int countView=0; //kurishlar soni

    private String sourceUrl; //manba
    private String sourceName; //manba


}
