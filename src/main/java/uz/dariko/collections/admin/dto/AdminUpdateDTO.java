package uz.dariko.collections.admin.dto;

import uz.dariko.base.dto.GenericDTO;

public class AdminUpdateDTO extends GenericDTO {
    private String username;

    private String password;

    private String imageID;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private Boolean isSuperAdmin;
}
