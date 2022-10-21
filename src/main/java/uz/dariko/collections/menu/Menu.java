package uz.dariko.collections.menu;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;

import javax.persistence.Entity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Menu extends Auditable {
    private String uzName;
    private String ruName;
    private String krName;

    private int rank;

}
