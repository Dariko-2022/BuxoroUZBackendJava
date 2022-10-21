package uz.dariko.collections.menu.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.SubMenuAdminDTO;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MenuDtoForAdmin {
    private List<MenuDTO> menuDTOS;
    private List<SubMenuAdminDTO> subMenuAdminDTOS;
}
