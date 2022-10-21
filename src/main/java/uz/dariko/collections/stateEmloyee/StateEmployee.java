package uz.dariko.collections.stateEmloyee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.file.File;
import uz.dariko.collections.govGroup.GovGroup;
import uz.dariko.collections.region.Region;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StateEmployee extends Auditable {

    private String firstName;
    private String lastName;
    private String patronymic;
    private Date birthDate;
    private String birthPlace;
    private String nation;

    @ManyToOne
    private Region region;
    private String degree;
    private String phoneNumber;


    @OneToOne
    private File image;

    @ManyToOne
    private GovGroup govGroup;


    private String responsibility; //vazifa va funksiyalari

    private String labor_activity; //mehnat faoliyati

    private int orderNumber;

}
