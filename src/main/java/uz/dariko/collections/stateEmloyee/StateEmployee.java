package uz.dariko.collections.stateEmloyee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.file.File;
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

    private String uzPosition;  //lavozimi
    private String ruPosition;  //lavozimi
    private String krPosition;  //lavozimi

    private Date birthDate;
    private String birthPlace;
    private String nation;



    @ManyToOne
    private Region region;

    private String degree;
    private String phoneNumber;

    private String additionalInformationUz;
    private String additionalInformationRu;
    private String additionalInformationKr;

    @OneToOne
    private File image;

    private String responsibility; //vazifa va funksiyalari

    private String labor_activity; //mehnat faoliyati

    private int orderNumber;

}
