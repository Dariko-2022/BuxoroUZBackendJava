package uz.dariko.collections.govSphere;


import lombok.*;
import uz.dariko.base.entity.Auditable;

import javax.persistence.Entity;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GovSphere extends Auditable {
    private String uzName;
    private String krName;
    private String ruName;
//    private String imgUrl;
}
