package uz.dariko.collections.newsSphere;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;

import javax.persistence.Entity;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class NewsSphere extends Auditable {

    private String uzName;
    private String krName;
    private String ruName;

}
