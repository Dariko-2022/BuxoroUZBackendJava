package uz.dariko.collections.infoGroup;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.menu.Menu;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InfoGroup extends Auditable {

    private String uzName;
    private String krName;
    private String ruName;

    @ManyToOne
    private Menu menu;

    private int rank;

    private boolean visible;

}
