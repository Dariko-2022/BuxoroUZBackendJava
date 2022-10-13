package uz.dariko.collections.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.collections.file.File;

import javax.persistence.OneToOne;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO extends GenericDTO {
    private String username;

    private String password;


    private String imageID;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Boolean isSuperAdmin;

    public AdminDTO(UUID id, String username, String password, String imageID, String firstName, String lastName, String email, String phoneNumber, Boolean isSuperAdmin) {
        super(id);
        this.username = username;
        this.password = password;
        this.imageID = imageID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isSuperAdmin = isSuperAdmin;
    }
}
