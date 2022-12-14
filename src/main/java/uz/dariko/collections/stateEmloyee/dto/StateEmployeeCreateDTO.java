package uz.dariko.collections.stateEmloyee.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
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

    private UUID submenuID;

    private Boolean isHokim=false;

    private String uzResponsibility;
    private String krResponsibility;
    private String ruResponsibility;

    private String facebook; //Hokim telegrami

    private String email;



}
