package uz.dariko.collections.menu;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.menu.dto.MenuCreateDTO;
import uz.dariko.collections.menu.dto.MenuDTO;
import uz.dariko.collections.menu.dto.MenuUpdateDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface MenuMapper  extends AbstractMapper<MenuCreateDTO, MenuUpdateDTO, MenuDTO,Menu> {
    @Override
    default MenuDTO toDto(Menu entity){
        MenuDTO menuDTO = new MenuDTO();
        menuDTO.setRank(entity.getRank());
        menuDTO.setId(entity.getId());
        menuDTO.setKrName(entity.getKrName());
        menuDTO.setRuName(entity.getRuName());
        menuDTO.setUzName(entity.getUzName());
        menuDTO.setVisible(entity.isVisible());
        return menuDTO;
    }

    default List<MenuDTO> toDto(List<Menu> list){
        List<MenuDTO> res = new ArrayList<>();
        for(Menu m : list) {
            res.add(toDto(m));
        }
        return res;
    }

    @Override
    default Menu fromCreateDto(MenuCreateDTO createDto){
        Menu menu = new Menu();
        menu.setKrName(createDto.getKrName());
        menu.setRuName(createDto.getRuName());
        menu.setUzName(createDto.getUzName());
        menu.setVisible(true);
        return menu;
    }


    default Menu fromUpdateDto(MenuUpdateDTO updateDto,Menu menu){
        menu.setUzName(updateDto.getUzName());
        menu.setRank(updateDto.getRank());
        menu.setRuName(updateDto.getRuName());
        menu.setKrName(updateDto.getKrName());
        menu.setVisible(updateDto.isVisible());
        return menu;
    }
}
