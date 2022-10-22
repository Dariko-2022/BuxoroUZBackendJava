package uz.dariko.collections.menu;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.dto.BaseOrderDTO;
import uz.dariko.base.dto.OrderDTO;
import uz.dariko.base.dto.SubMenuAdminDTO;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.govGroup.GovGroup;
import uz.dariko.collections.govGroup.GovGroupRepository;
import uz.dariko.collections.govGroup.GovGroupService;
import uz.dariko.collections.infoGroup.InfoGroup;
import uz.dariko.collections.infoGroup.InfoGroupRepository;
import uz.dariko.collections.infoGroup.InfoGroupService;
import uz.dariko.collections.menu.dto.MenuCreateDTO;
import uz.dariko.collections.menu.dto.MenuDTO;
import uz.dariko.collections.menu.dto.MenuDtoForAdmin;
import uz.dariko.collections.menu.dto.MenuUpdateDTO;
import uz.dariko.collections.sphere.Sphere;
import uz.dariko.collections.sphere.SphereRepository;
import uz.dariko.collections.sphere.SphereService;
import uz.dariko.response.Data;
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

    private final SphereRepository sphereRepository;
    private final GovGroupRepository govGroupRepository;
    private final InfoGroupRepository infoGroupRepository;
    private final GovGroupService govGroupService;
    private final InfoGroupService infoGroupService;
    private final SphereService sphereService;

    private final EntityGetter entityGetter;

    public MenuService(MenuMapper menuMapper, MenuRepository menuRepository, SphereRepository sphereRepository, GovGroupRepository govGroupRepository, InfoGroupRepository infoGroupRepository, GovGroupService govGroupService, InfoGroupService infoGroupService, SphereService sphereService, EntityGetter entityGetter) {
        this.menuMapper = menuMapper;
        this.menuRepository = menuRepository;
        this.sphereRepository = sphereRepository;
        this.govGroupRepository = govGroupRepository;
        this.infoGroupRepository = infoGroupRepository;

        this.govGroupService = govGroupService;

        this.infoGroupService = infoGroupService;
        this.sphereService = sphereService;
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

    public ResponseEntity<?> delete(UUID uuid){

        Menu menu = entityGetter.getMenu(uuid);
        List<Sphere> list1 = sphereRepository.findByMenuAndDeleted(uuid, false);
        List<GovGroup> list2 = govGroupRepository.findByMenuAndDeleted(uuid, false);
        List<InfoGroup> list3 = infoGroupRepository.findByMenuAndDeleted(uuid, false);
        if(list1.isEmpty()&list2.isEmpty()&list3.isEmpty()) {
            menu.setDeleted(true);
            menu.setDeletedAt(LocalDateTime.now());
            menuRepository.save(menu);
            return ResponseEntity.status(200).body(true);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Menu o'chirib bo'lmaydi!!! Avval menu ostilarini o'chiring!");
    }

    public ResponseEntity<?> get(UUID uuid) {
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
        List<MenuDTO> menuDTOS = menuMapper.toDto(allByDeletedNot);
        List<SubMenuAdminDTO> forAdmin = govGroupService.getForAdmin();
        List<SubMenuAdminDTO> forAdmin1 = infoGroupService.getForAdmin();
        List<SubMenuAdminDTO> forAdmin2 = sphereService.getForAdmin();
        forAdmin.addAll(forAdmin1);
        forAdmin.addAll(forAdmin2);
        MenuDtoForAdmin menuDtoForAdmin = new MenuDtoForAdmin();
        menuDtoForAdmin.setSubMenuAdminDTOS(forAdmin);
        menuDtoForAdmin.setMenuDTOS(menuDTOS);
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
