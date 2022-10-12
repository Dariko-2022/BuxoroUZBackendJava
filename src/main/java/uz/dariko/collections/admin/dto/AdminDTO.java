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
public class AdminDTO extends GenericDTO {
    private String username;

    private String password;

    private String imgUrl;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;




    public AdminDTO(UUID id,String username, String password, String imgUrl, String firstName, String lastName, String email, String phoneNumber) {
        super(id);
        this.username = username;
        this.password = password;
        this.imgUrl = imgUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
