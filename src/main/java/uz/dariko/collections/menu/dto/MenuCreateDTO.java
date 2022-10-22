package uz.dariko.collections.menu.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenuCreateDTO implements BaseDTO {
    private String uzName;
    private String ruName;
    private String krName;
    private int rank;
    private boolean visible=true;
}

