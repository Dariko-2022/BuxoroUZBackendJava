package uz.dariko.collections.stateEmloyee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StateEmployeeDTO extends GenericDTO {
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
