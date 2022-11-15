package uz.dariko.collections.sector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uz.dariko.base.entity.Auditable;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.file.File;
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
public class Sector extends Auditable implements BaseService {

    private String uzName;
    private String krName;
    private String ruName;

    @OneToOne
    private File file;

    @OneToOne
    private StateEmployee stateEmployee;


    @Type(type = "text")
    private String uzDescription;
    @Type(type = "text")
    private String krDescription;
    @Type(type = "text")
    private String ruDescription;


    @Type(type = "text")
    private String uzSectorArea;
    @Type(type = "text")
    private String krSectorArea;
    @Type(type = "text")
    private String ruSectorArea;

    private int orderNumber;

}
