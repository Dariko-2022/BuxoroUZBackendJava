package uz.dariko.collections.stateEmloyee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;
import uz.dariko.collections.file.File;
import uz.dariko.collections.region.Region;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StateCreateDTO implements BaseDTO {
    private String firstName;
    private String lastName;
    private String patronymic;
    private Date birthDate;
    private String birthPlace;
    private String nation;

    private String regionID;
    private String degree;
    private String phoneNumber;


    private String imageID;

    private String responsibility;

    private String labor_activity; //mehnat faoliyati

}
