package uz.dariko.collections.sector;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    @ManyToOne
    private Region region;

    private String description;

    private String sectorArea;

}
