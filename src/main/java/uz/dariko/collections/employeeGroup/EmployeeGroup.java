package uz.dariko.collections.employeeGroup;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.menu.Menu;
import uz.dariko.collections.stateEmloyee.StateEmployee;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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

    @ManyToMany
    List<StateEmployee> stateEmployeeList;

    @ManyToOne
    private Menu menu;
}
