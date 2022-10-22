package uz.dariko.collections.subGovGroup;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.govGroup.GovGroup;
import uz.dariko.collections.menu.Menu;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SubGovGroup extends Auditable {


    private String uzName;
    private String ruName;
    private String krName;

    private String uzDescription;
    private String ruDescription;
    private String krDescription;

    @ManyToOne
    private GovGroup govGroup;


    private int rank;

    private boolean visible;

}
