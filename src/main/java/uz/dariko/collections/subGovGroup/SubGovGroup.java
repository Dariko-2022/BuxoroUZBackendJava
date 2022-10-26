package uz.dariko.collections.subGovGroup;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.govGroup.GovGroup;
import uz.dariko.collections.stateEmloyee.StateEmployee;

import javax.persistence.*;
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

    private String uzDescription;
    private String ruDescription;
    private String krDescription;

    private String uzTitle;
    private String ruTitle;
    private String krTitle;

    @ManyToOne
    private GovGroup govGroup;

    @ManyToMany
    List<StateEmployee> stateEmployees;


    private int rank;

}