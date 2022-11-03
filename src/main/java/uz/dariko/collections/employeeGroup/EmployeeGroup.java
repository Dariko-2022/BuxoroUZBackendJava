package uz.dariko.collections.employeeGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.stateEmloyee.StateEmployee;
import uz.dariko.collections.submenu.Submenu;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmployeeGroup extends Auditable {

    private String uzName;
    private String ruName;
    private String krName;

    @OneToMany
    List<StateEmployee> employeeList = new ArrayList<>();

    @OneToOne
    private Submenu submenu;
}
