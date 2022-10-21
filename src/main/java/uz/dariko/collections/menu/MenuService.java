package uz.dariko.collections.menu;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.dariko.base.dto.SubMenuAdminDTO;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.govGroup.GovGroupService;
import uz.dariko.collections.infoGroup.InfoGroupService;
import uz.dariko.collections.menu.dto.MenuCreateDTO;
import uz.dariko.collections.menu.dto.MenuDTO;
import uz.dariko.collections.menu.dto.MenuDtoForAdmin;
import uz.dariko.collections.menu.dto.MenuUpdateDTO;
import uz.dariko.collections.sphere.SphereService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MenuService implements BaseService {

    private final MenuMapper menuMapper;
    private final MenuRepository menuRepository;
    private final GovGroupService govGroupService;
    private final InfoGroupService infoGroupService;
    private final SphereService sphereService;

    public MenuService(MenuMapper menuMapper, MenuRepository menuRepository, GovGroupService govGroupService, InfoGroupService infoGroupService, SphereService sphereService) {
        this.menuMapper = menuMapper;
        this.menuRepository = menuRepository;

        this.govGroupService = govGroupService;

        this.infoGroupService = infoGroupService;
        this.sphereService = sphereService;
    }


    public ResponseEntity<?> create(MenuCreateDTO menuCreateDTO) {
        Menu menu = menuMapper.fromCreateDto(menuCreateDTO);
        Menu save = menuRepository.save(menu);
        MenuDTO menuDTO = menuMapper.toDto(save);
        return ResponseEntity.status(201).body(menuDTO);

    }

    public ResponseEntity<?> update(MenuUpdateDTO dto) {
        Optional<Menu> byId = menuRepository.findById(dto.getId());
        if(byId.isEmpty()) {
            return ResponseEntity.status(404).body(false);
        }
        Menu menu = menuMapper.fromUpdateDto(dto, byId.get());
        Menu save = menuRepository.save(menu);
        MenuDTO menuDTO = menuMapper.toDto(save);
        return ResponseEntity.status(202).body(menuDTO);
    }

    public ResponseEntity<?> delete(UUID uuid){
        Optional<Menu> byId = menuRepository.findById(uuid);
        if(byId.isEmpty()) {
            return ResponseEntity.status(404).body(false);
        }
        Menu menu = byId.get();
        menu.setDeleted(true);
        menu.setDeletedAt(LocalDateTime.now());
        menuRepository.save(menu);
        return ResponseEntity.status(200).body(true);
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


}
