package uz.dariko.collections.region;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.file.File;
import uz.dariko.collections.stateEmloyee.StateEmployee;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Region extends Auditable {

    private String uzName;
    private String krName;
    private String ruName;

    private String imgUrl;//header chapdagi rasm

    private String uzDescription;
    private String krDescription;
    private String ruDescription;

    @OneToOne
    private StateEmployee hokim;

    @OneToOne
    private File file;

    //hokim id sini saqlash kerak;

    private String hotline;//ishonch telefoni

    private String email;

    private String facebookLink;

    private String instagramLink;

    private String youtubeLink;

    private String telegramLink;

}
