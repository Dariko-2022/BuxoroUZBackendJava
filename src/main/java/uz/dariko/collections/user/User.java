package uz.dariko.collections.user;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;
import uz.dariko.base.entity.Auditable;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends Auditable {

    private String username;

    private String password;

    private String imgUrl;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    @ManyToOne
    private UserRole userRole;


}
