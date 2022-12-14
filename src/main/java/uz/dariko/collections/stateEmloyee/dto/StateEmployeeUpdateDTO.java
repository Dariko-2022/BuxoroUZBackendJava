package uz.dariko.collections.stateEmloyee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StateEmployeeUpdateDTO extends GenericDTO {
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

    private UUID imageID;

    private String uzAdditionalInformation;
    private String ruAdditionalInformation;
    private String krAdditionalInformation;

    private Boolean isHokim=false;

    private String uzResponsibility;
    private String krResponsibility;
    private String ruResponsibility;

    private String facebook; //Hokim facebook

    private String email;

    private int orderNumber;

}
