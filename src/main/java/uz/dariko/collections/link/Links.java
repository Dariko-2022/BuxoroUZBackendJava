package uz.dariko.collections.link;

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
public class Links extends Auditable {
    private String uzName;
    private String krName;
    private String ruName;
    private String imgUrl;
    private String url;
}
