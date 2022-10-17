package uz.dariko.collections.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminUpdateDTO extends GenericDTO {
    private String username;

    private String password;

    private UUID imageID;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Boolean isSuperAdmin;
}
