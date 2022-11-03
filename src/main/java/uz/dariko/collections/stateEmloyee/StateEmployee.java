package uz.dariko.collections.stateEmloyee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.file.File;

import javax.persistence.Entity;
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
    private String uzBirthPlace;
    private String krBirthPlace;
    private String ruBirthPlace;
    private String nation;


    private String degree;
    private String phoneNumber;

    @Type(type = "text")
    private String additionalInformationUz;
    @Type(type = "text")
    private String additionalInformationRu;
    @Type(type = "text")
    private String additionalInformationKr;

    @OneToOne
    private File image;

    @Type(type = "text")
    private String uzResponsibility;
    @Type(type = "text")
    private String krResponsibility;
    @Type(type = "text")
    private String ruResponsibility; //vazifa va funksiyalari

    private String labor_activity; //mehnat faoliyati

    private int orderNumber;

}
