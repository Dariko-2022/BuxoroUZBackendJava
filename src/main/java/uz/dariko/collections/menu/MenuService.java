package uz.dariko.collections.menu;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.dariko.base.dto.BaseOrderDTO;
import uz.dariko.base.dto.OrderDTO;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.menu.dto.MenuCreateDTO;
import uz.dariko.collections.menu.dto.MenuDTO;
import uz.dariko.collections.menu.dto.MenuDtoForAdmin;
import uz.dariko.collections.menu.dto.MenuUpdateDTO;
import uz.dariko.collections.submenu.Submenu;
import uz.dariko.collections.submenu.SubmenuMapper;
import uz.dariko.collections.submenu.SubmenuRepository;
import uz.dariko.collections.submenu.SubmenuService;
import uz.dariko.collections.submenu.dto.SubmenuDTO;
import uz.dariko.response.Data;
import uz.dariko.utils.BaseUtils;
import uz.dariko.utils.EntityGetter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuService implements BaseService {

    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;

    private final SubmenuRepository submenuRepository;
    private final SubmenuService submenuService;
    private final SubmenuMapper submenuMapper;
    private final BaseUtils baseUtils;
    private final EntityGetter entityGetter;

    public MenuService(MenuMapper menuMapper, MenuRepository menuRepository, SubmenuRepository submenuRepository, SubmenuService submenuService, SubmenuMapper submenuMapper, BaseUtils baseUtils, EntityGetter entityGetter) {
        this.menuMapper = menuMapper;
        this.menuRepository = menuRepository;
        this.submenuRepository = submenuRepository;
        this.submenuService = submenuService;
        this.submenuMapper = submenuMapper;
        this.baseUtils = baseUtils;
        this.entityGetter = entityGetter;
    }


    public ResponseEntity<?> create(MenuCreateDTO menuCreateDTO) {
        Menu menu = menuMapper.fromCreateDto(menuCreateDTO);
        menu.setRank(menuRepository.getTotalCount()+1);
        Menu save = menuRepository.save(menu);
        MenuDTO menuDTO = menuMapper.toDto(save);
        return ResponseEntity.status(201).body(menuDTO);

    }


    public ResponseEntity<?> update(MenuUpdateDTO dto) {
        Menu menu = entityGetter.getMenu(dto.getId());
        Menu menu1 = menuMapper.fromUpdateDto(dto, menu);
        Menu save = menuRepository.save(menu1);
        MenuDTO menuDTO = menuMapper.toDto(save);
        return ResponseEntity.status(202).body(menuDTO);
    }

    public ResponseEntity<?> delete(String code){
        UUID uuid = baseUtils.parseUUID(code);
        Menu menu = entityGetter.getMenu(uuid);
        List<Submenu> list1 = submenuRepository.findByMenuAndDeleted(uuid, false);
        if(list1.isEmpty()) {
            menu.setDeleted(true);
            Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            menu.setDeletedBy(sessionUser.getId());
            menu.setDeletedAt(LocalDateTime.now());
            menuRepository.save(menu);
            return ResponseEntity.status(200).body(true);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Menu o'chirib bo'lmaydi!!! Avval menu ostilarini o'chiring!");
    }

    public ResponseEntity<?> get(String code) {
        UUID uuid = baseUtils.parseUUID(code);
        Optional<Menu> byIdAndDeletedNot = menuRepository.findByIdAndDeletedNot(uuid);
        if(byIdAndDeletedNot.isEmpty()) {
            return ResponseEntity.status(404).body(false);
        }
        Menu menu = byIdAndDeletedNot.get();
        return ResponseEntity.ok(menu);
    }

    public ResponseEntity<?> get() {
        List<Menu> allByDeletedNot = menuRepository.findAllByDeletedNot();
        if(allByDeletedNot.isEmpty()) {
            return ResponseEntity.status(404).body(false);
        }
        return ResponseEntity.ok(allByDeletedNot);
    }

    public ResponseEntity<?> getForAdmin() {
        List<Menu> allByDeletedNot = menuRepository.findAllByDeletedNot();
        List<Submenu> submenuList = submenuRepository.findAllByDeleted(false);
        List<MenuDTO> menuDTOS = menuMapper.toDto(allByDeletedNot);
        List<SubmenuDTO> submenuDTOS = submenuMapper.toDto(submenuList);
        MenuDtoForAdmin menuDtoForAdmin = new MenuDtoForAdmin();
        menuDtoForAdmin.setMenuDTOS(menuDTOS);
        menuDtoForAdmin.setSubmenuDTOS(submenuDTOS);
        return ResponseEntity.ok(menuDtoForAdmin);
    }

    public ResponseEntity<Data<List<MenuDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
        List<OrderDTO> orderDTOS = baseOrderDTO.getOrders();
        List<Menu> entities = new ArrayList<>();
        for (OrderDTO order : orderDTOS) {
            Menu entity = menuRepository.setOrOrderNumber(order.getId(), order.getOrder());
            entities.add(entity);
        }
        return new ResponseEntity<>(new Data<>(menuMapper.toDto(entities)), HttpStatus.OK);
    }
}
