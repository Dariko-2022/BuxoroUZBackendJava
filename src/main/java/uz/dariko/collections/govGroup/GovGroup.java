package uz.dariko.collections.govGroup;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.menu.Menu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GovGroup extends Auditable {


    private String uzName;
    private String ruName;
    private String krName;

    private String uzDescription;
    private String ruDescription;
    private String krDescription;

    @ManyToOne
    private Menu menu;

    private int rank;

    private boolean visible;

}
