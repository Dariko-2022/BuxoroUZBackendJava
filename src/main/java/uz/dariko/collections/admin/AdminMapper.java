package uz.dariko.collections.admin;

import org.springframework.stereotype.Component;
import uz.dariko.collections.admin.dto.AdminDTO;

@Component
public class AdminMapper {

    public AdminDTO toDTO(Admin admin) {
        if (admin == null) {
            return null;
        }
        String aboutMe = null;
        /*if (admin.getAboutMe() != null) {
            aboutMe = admin.getAboutMe().getGeneratedName();
        }*/
        return new AdminDTO(admin
                .getId(), admin.getUsername(), admin.getPassword(), admin.getImgUrl(), admin.getFirstName(), admin.getLastName(), admin.getEmail(),
                admin.getPhoneNumber());
    }
}
