package uz.dariko.collections.sphere;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.menu.Menu;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sphere extends Auditable { //soha
    private String uzName;
    private String krName;
    private String ruName;

    @ManyToOne
    private Menu menu;

    private int rank;

    private boolean visible;


}
