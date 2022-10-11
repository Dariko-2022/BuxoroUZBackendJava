package uz.dariko.collections.sphere;

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
public class Sphere extends Auditable { //soha
    private String uzName;
    private String krName;
    private String ruName;
    private String imgUrl;

}
