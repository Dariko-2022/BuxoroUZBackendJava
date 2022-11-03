package uz.dariko.collections.subGovGroup;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.dariko.collections.admin.Admin;
import uz.dariko.collections.stateEmloyee.StateEmployee;
import uz.dariko.collections.subGovGroup.dto.*;
import uz.dariko.base.service.BaseService;
import uz.dariko.response.Data;
import uz.dariko.utils.BaseUtils;
import uz.dariko.utils.EntityGetter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class SubGovGroupService implements BaseService {

    private final SubGovGroupRepository subGovGroupRepository;

    private final SubGovGroupMapper subGovGroupMapper;

    private final EntityGetter entityGetter;
    private final BaseUtils baseUtils;


    public SubGovGroupService(SubGovGroupRepository govGroupRepository, SubGovGroupMapper subGovGroupMapper, EntityGetter entityGetter, BaseUtils baseUtils) {
        this.subGovGroupRepository = govGroupRepository;
        this.subGovGroupMapper = subGovGroupMapper;
        this.entityGetter = entityGetter;
        this.baseUtils = baseUtils;
    }

    public ResponseEntity<?> create(SubGovGroupCreateDTO dto) {
        List<SubGovGroup> allBySubmenuId = subGovGroupRepository.findAllBySubmenuId(dto.getSubmenuId());
        SubGovGroup subGovGroup = subGovGroupMapper.fromCreateDto(dto,allBySubmenuId.size());
        subGovGroup.setSubmenu(entityGetter.getSubmenu(dto.getSubmenuId()));
        SubGovGroup save = subGovGroupRepository.save(subGovGroup);
        SubGovGroupDTO govGroupDTO = subGovGroupMapper.toDto(save);
        return ResponseEntity.status(201).body(govGroupDTO);
    }

    public ResponseEntity<?> updateDescription(SubGovGroupDescriptionUpdateDTO dto) {
        SubGovGroup subGovGroup = entityGetter.getSubGovGroup(dto.getId());
        subGovGroup.setKrDescription(dto.getKrDescription());
        subGovGroup.setUzDescription(dto.getUzDescription());
        subGovGroup.setRuDescription(dto.getRuDescription());
        SubGovGroup save = subGovGroupRepository.save(subGovGroup);
        SubGovGroupDTO subGovGroupDTO = subGovGroupMapper.toDto(save);
        return ResponseEntity.ok(subGovGroupDTO);

    }


    public ResponseEntity<?> updateName(SubGovGroupNameUpdateDTO dto) {
        SubGovGroup subGovGroup = subGovGroupMapper.fromNameUpdateDto(dto);
        SubGovGroup save = subGovGroupRepository.save(subGovGroup);
        SubGovGroupDTO subGovGroupDTO = subGovGroupMapper.toDto(save);
        return ResponseEntity.ok(subGovGroupDTO);

    }

    public ResponseEntity<?> update(SubGovGroupUpdateDTO dto) {

        SubGovGroup govGroup = entityGetter.getSubGovGroup(dto.getId());
        govGroup.setUzName(dto.getUzName());
        govGroup.setKrName(dto.getKrName());
        govGroup.setRuName(dto.getRuName());
        //
        Admin sessionUser= (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        govGroup.setUpdatedBy(sessionUser.getId());
        govGroup.setUpdatedAt(LocalDateTime.now());
        //
        govGroup.setRank(dto.getRank());
        govGroup.setSubmenu(entityGetter.getSubmenu(dto.getSubmenuId()));
        subGovGroupRepository.save(govGroup);
        return ResponseEntity.status(204).body("updated");
    }

    public ResponseEntity<?> delete(UUID id) {

        SubGovGroup subGovGroup = entityGetter.getSubGovGroup(id);
        boolean b= true;
        List<StateEmployee> employeeList = subGovGroup.getEmployeeList();
        for(StateEmployee e : employeeList) {
            b = e.isDeleted();
        }
        if(b) {
            //
            Admin sessionUser = (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            subGovGroup.setDeletedBy(sessionUser.getId());
            subGovGroup.setDeletedAt(LocalDateTime.now());
            //
            subGovGroup.setDeleted(true);
            subGovGroupRepository.save(subGovGroup);
            return ResponseEntity.status(204).body(true);
        }
        else {
            return ResponseEntity.status(400).body("odam bor ichida");
        }
    }

    public ResponseEntity<?> getById(UUID id) {

        SubGovGroup subGovGroup = entityGetter.getSubGovGroup(id);
        SubGovGroupDTO subGovGroupDTO = subGovGroupMapper.toDto(subGovGroup);
        return ResponseEntity.status(200).body(subGovGroupDTO);


    }

    public ResponseEntity<?> getList() {
        List<SubGovGroup> list = subGovGroupRepository.findAllByDeleted(false);
        List<SubGovGroupDTO> subGovGroupDTOS = subGovGroupMapper.toDto(list);
        return ResponseEntity.ok(subGovGroupDTOS);

    }

    public ResponseEntity<?> getBySubmenuId(UUID code) {
        List<SubGovGroup> allBySubmenuId = subGovGroupRepository.findAllBySubmenuIdAndDeletedNot(code);
        List<SubGovGroupDTO> subGovGroupDTOS = subGovGroupMapper.toDto(allBySubmenuId);

        return ResponseEntity.ok(subGovGroupDTOS);
    }

    public void addEmployee(UUID subGovGroupId,StateEmployee stateEmployee) {
        SubGovGroup subGovGroup = entityGetter.getSubGovGroup(subGovGroupId);
        List<StateEmployee> employeeList = subGovGroup.getEmployeeList();
        employeeList.add(stateEmployee);
        subGovGroup.setEmployeeList(employeeList);
        subGovGroupRepository.save(subGovGroup);

    }

}
