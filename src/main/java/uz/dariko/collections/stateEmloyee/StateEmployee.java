package uz.dariko.collections.stateEmloyee;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.region.Region;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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
    private boolean isSenator;
    private boolean isDeputy;

    @ManyToOne
    private Region region;

    private String degree;
    private String phoneNumber;
    private String imgUrl;

}