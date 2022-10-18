package uz.dariko.collections.admin;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.file.File;

import javax.persistence.*;
import java.util.Collection;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin extends Auditable implements UserDetails {

    private String username;

    private String password;

    @OneToOne
    private File image;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

   private Boolean isSuperAdmin;

    public Admin(UUID id, String username, String password, File image, String firstName, String lastName, String email, String phoneNumber, Boolean isSuperAdmin) {
        super(id);
        this.username = username;
        this.password = password;
        this.image = image;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isSuperAdmin = isSuperAdmin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.isDeleted();
    }
}
