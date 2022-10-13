package uz.dariko.collections.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminCreateDTO implements BaseDTO {
    private String username;

    private String password;


    private String imageID;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Boolean isSuperAdmin;
}
