package uz.dariko.collections.sector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.region.Region;
import uz.dariko.collections.stateEmloyee.StateEmployee;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sector extends Auditable {

    private String uzName;
    private String krName;
    private String ruName;

    private String imgUrl;

    @OneToOne
    private StateEmployee stateEmployee;

    @ManyToOne
    private Region region;

    private String hotline;//ishonch telefoni

    private String email;

    private String facebookLink;

    private String instagramLink;

    private String youtubeLink;

    private String telegramLink;

}
