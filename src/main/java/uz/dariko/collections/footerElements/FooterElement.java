package uz.dariko.collections.footerElements;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uz.dariko.base.entity.Auditable;

import javax.persistence.Entity;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FooterElement extends Auditable {

    private String body;

    @Type(type = "text")
    private String uzRegulations;
    @Type(type = "text")
    private String krRegulations;
    @Type(type = "text")
    private String ruRegulations;

    private int type;

}
