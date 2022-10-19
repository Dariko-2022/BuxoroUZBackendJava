package uz.dariko.collections.govGroup;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;

import javax.persistence.Column;
import javax.persistence.Entity;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GovGroup extends Auditable {


    @Column(unique = true)
    private String name;

    private String description;

}
