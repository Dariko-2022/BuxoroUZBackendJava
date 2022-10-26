package uz.dariko.collections.stateEmloyee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StateEmployeeCreateDTO implements BaseDTO {
    private String firstName;
    private String lastName;
    private String patronymic;
    private Date birthDate;
    private String birthPlace;
    private String nation;

    private UUID regionID;
    private String degree;
    private String phoneNumber;


    private UUID imageID;


    private String responsibility;

    private String labor_activity; //mehnat faoliyati


}
