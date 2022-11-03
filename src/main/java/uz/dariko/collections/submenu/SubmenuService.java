package uz.dariko.collections.submenu;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.dariko.base.dto.BaseOrderDTO;
import uz.dariko.base.dto.OrderDTO;
import uz.dariko.base.dto.SubMenuAdminDTO;
import uz.dariko.base.service.BaseService;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.employeeGroup.EmployeeGroup;
import uz.dariko.collections.employeeGroup.EmployeeGroupService;
import uz.dariko.collections.employeeGroup.dto.EmployeeGroupCreateDTO;
import uz.dariko.collections.menu.Menu;
import uz.dariko.collections.submenu.dto.SubmenuCreateDTO;
import uz.dariko.collections.submenu.dto.SubmenuDTO;
import uz.dariko.collections.submenu.dto.SubmenuUpdateDTO;
import uz.dariko.response.Data;
import uz.dariko.utils.EntityGetter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class SubmenuService implements BaseService{

    private final SubmenuRepository submenuRepository;

    private final SubmenuMapper submenuMapper;

    private final EmployeeGroupService employeeGroupService;

    private final EntityGetter entityGetter;

    public SubmenuService(SubmenuRepository submenuRepository, SubmenuMapper submenuMapper, EmployeeGroupService employeeGroupService, EntityGetter entityGetter) {
        this.submenuRepository = submenuRepository;
        this.submenuMapper = submenuMapper;
        this.employeeGroupService = employeeGroupService;
        this.entityGetter = entityGetter;
    }

    public ResponseEntity<?> create(SubmenuCreateDTO dto) {
        if(dto.getType().equals("employeeGroup") || dto.getType().equals("subGovGroup")) {
            return createForEmployeeGroup(dto);
        }else {
            return createForNews(dto);
        }
    }

    public ResponseEntity<?> createForNews(SubmenuCreateDTO dto) {
        Submenu submenu = submenuMapper.fromCreateDto(dto);
        submenu.setMenu(entityGetter.getMenu(dto.getMenuId()));
        submenu.setRank(submenuRepository.getTotalCount(dto.getMenuId())+1);
        Submenu save = submenuRepository.save(submenu);

        SubmenuDTO submenuDTO = submenuMapper.toDto(save);
        return ResponseEntity.status(201).body(submenuDTO);
    }

    public ResponseEntity<?> createForEmployeeGroup(SubmenuCreateDTO dto) {
        Submenu submenu = submenuMapper.fromCreateDto(dto);
        submenu.setMenu(entityGetter.getMenu(dto.getMenuId()));
        submenu.setRank(submenuRepository.getTotalCount(dto.getMenuId())+1);

        Submenu save = submenuRepository.save(submenu);
        SubmenuDTO submenuDTO = submenuMapper.toDto(save);
        EmployeeGroup employeeGroup = null;
        if(save.getType().equals("employeeGroup")) {
            EmployeeGroupCreateDTO dto1 = new EmployeeGroupCreateDTO(dto.getUzName(), dto.getRuName(), dto.getKrName(), save.getId());
            employeeGroup = employeeGroupService.createForSubmenu(dto1);
            submenuDTO.setEmployeeGroupId(employeeGroup.getId());
        }

        return ResponseEntity.status(201).body(submenuDTO);
    }

    public ResponseEntity<?> getAll() {
        List<Submenu> allByDeleted = submenuRepository.findAllByDeleted(false);
        return ResponseEntity.ok(allByDeleted);
    }

    public ResponseEntity<?> update(SubmenuUpdateDTO dto) {

        Submenu submenu = entityGetter.getSphere(dto.getId());

        submenu.setUzName(dto.getUzName());
        submenu.setKrName(dto.getKrName());
        submenu.setRuName(dto.getRuName());
        submenu.setVisible(dto.isVisible());
        submenu.setUrl(dto.getUrl());
        submenu.setMenu(entityGetter.getMenu(dto.getMenuId()));
        //
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        submenu.setUpdatedBy(sessionUser.getId());
        submenu.setUpdatedAt(LocalDateTime.now());
        //
        Submenu save = submenuRepository.save(submenu);
        SubmenuDTO res = submenuMapper.toDto(save);
        return ResponseEntity.status(204).body(res);

    }

    public ResponseEntity<?> delete(UUID id) {
        Optional<Submenu> byId = submenuRepository.findById(id);
        if(byId.isPresent()){
            Submenu submenu = byId.get();
            Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            submenu.setDeletedBy(sessionUser.getId());
            submenu.setDeletedAt(LocalDateTime.now());
            submenu.setDeleted(true);
            submenu.setDeletedAt(LocalDateTime.now());
            submenuRepository.save(submenu);
            return ResponseEntity.status(204).body("Deleted");
        }
        return ResponseEntity.status(404).body("Not Found");
    }

    public ResponseEntity<?> get(UUID id) {
        Optional<Submenu> byId = submenuRepository.findById(id);
        if(byId.isEmpty()) {
            return ResponseEntity.status(404).body("Not Found");
        }
        Submenu submenu = byId.get();
        SubmenuDTO submenuDTO = submenuMapper.toDto(submenu);
        return ResponseEntity.ok(submenuDTO);
    }

    public List<SubMenuAdminDTO> getForAdmin(){
        List<Submenu> list = submenuRepository.findAllByDeleted(false);
        return submenuMapper.toAdminDto(list);
    }

    public ResponseEntity<Data<List<SubmenuDTO>>> changeOrder(BaseOrderDTO baseOrderDTO) {
        List<OrderDTO> orderDTOS = baseOrderDTO.getOrders();
        List<Submenu> entities = new ArrayList<>();
        for (OrderDTO order : orderDTOS) {
            Submenu entity = submenuRepository.setOrOrderNumber(order.getId(), order.getOrder());
            entities.add(entity);
        }
        return new ResponseEntity<>(new Data<>(submenuMapper.toDto(entities)), HttpStatus.OK);
    }

    public ResponseEntity<?> changeMenu(UUID submenuId,UUID menuId){
        Submenu submenu = entityGetter.getSphere(submenuId);
        Menu menu = entityGetter.getMenu(menuId);
        submenu.setMenu(menu);
        Submenu save = submenuRepository.save(submenu);
        return ResponseEntity.ok(save);
    }

}
