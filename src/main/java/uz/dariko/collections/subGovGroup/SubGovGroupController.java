package uz.dariko.collections.subGovGroup;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.collections.subGovGroup.dto.*;

import java.util.UUID;


@RestController
@RequestMapping("subGovGroup")
public class SubGovGroupController extends AbstractController<SubGovGroupService> implements GenericCRUDController<SubGovGroupCreateDTO, SubGovGroupUpdateDTO, SubGovGroupDTO, UUID> {
    public SubGovGroupController(SubGovGroupService service) {
        super(service);
    }

    @Override
    public ResponseEntity<?> create(SubGovGroupCreateDTO DTO) throws Exception {
        return service.create(DTO);
    }

    @Override
    public ResponseEntity<?> update(SubGovGroupUpdateDTO DTO) {
        return service.update(DTO);
    }

    @PatchMapping("/updateDescription")
    public ResponseEntity<?> updateDescription(
            @RequestBody SubGovGroupDescriptionUpdateDTO dto) {
        return service.updateDescription(dto);
    }

    @PatchMapping("/updateName")
    public ResponseEntity<?> updateDescription(
            @RequestBody SubGovGroupNameUpdateDTO dto) {
        return service.updateName(dto);
    }

    @Override
    public ResponseEntity<?> delete(
            @PathVariable("code") UUID code) {
        return service.delete(code);
    }

    @Override
    public ResponseEntity<?> get(
            @PathVariable("code") UUID code) {
        return service.getById(code);
    }

    @Override
    public ResponseEntity<?> list() {
        return service.getList();
    }



    @GetMapping("/getBySubmenuId")
    public ResponseEntity<?> getBySubmenuId( @RequestParam(name = "code") UUID code){
        return service.getBySubmenuId(code);
    }
}
