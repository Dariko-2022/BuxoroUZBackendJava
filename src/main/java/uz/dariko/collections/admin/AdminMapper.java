package uz.dariko.collections.admin;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.BaseMapper;
import uz.dariko.collections.admin.dto.AdminDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper extends BaseMapper {

    default AdminDTO toDTO(Admin admin) {
        if (admin == null) {
            return null;
        }
        String aboutMe = null;
        if (admin.getImage() != null) {
            aboutMe = admin.getImage().getGeneratedName();
        }
        return new AdminDTO(admin
                .getId(), admin.getUsername(), admin.getPassword(), aboutMe, admin.getFirstName(), admin.getLastName(), admin.getEmail(),
                admin.getPhoneNumber(),admin.getIsSuperAdmin());
    }

    default List<AdminDTO> toDTO(List<Admin> admins){
        List<AdminDTO> adminDTOS=new ArrayList<>();
        for (Admin admin : admins) {
            adminDTOS.add(toDTO(admin));
        }

        return adminDTOS;
    }
}
