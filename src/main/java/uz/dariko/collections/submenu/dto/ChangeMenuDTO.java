package uz.dariko.collections.submenu.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangeMenuDTO {
    private UUID submenuId;
    private UUID menuId;
}
