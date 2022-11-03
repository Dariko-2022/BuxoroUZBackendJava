package uz.dariko.collections.subGovGroup;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.stateEmloyee.StateEmployee;
import uz.dariko.collections.submenu.Submenu;

import javax.persistence.*;
import java.util.ArrayList;
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

    private String uzDescriptionTitle;
    private String ruDescriptionTitle;
    private String krDescriptionTitle;


    @Type(type = "text")
    private String uzDescription;
    @Type(type = "text")
    private String ruDescription;
    @Type(type = "text")
    private String krDescription;

    private String uzTitle;
    private String ruTitle;
    private String krTitle;

    @ManyToOne
    private Submenu submenu;

    @OneToMany
    private List<StateEmployee> employeeList = new ArrayList<>();

    private int rank;

}