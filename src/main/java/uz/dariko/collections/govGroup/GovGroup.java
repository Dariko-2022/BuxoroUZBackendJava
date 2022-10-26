package uz.dariko.collections.govGroup;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.menu.Menu;
import uz.dariko.collections.subGovGroup.SubGovGroup;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GovGroup extends Auditable {

    private String uzName;
    private String ruName;
    private String krName;

    @ManyToOne
    private Menu menu;


    private int rank;

    private boolean visible;

}
