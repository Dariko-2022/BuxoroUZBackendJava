package uz.dariko.collections.link;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.base.controller.GenericCRUDController;
import uz.dariko.collections.link.dto.LinkCreateDTO;
import uz.dariko.collections.link.dto.LinkDTO;
import uz.dariko.collections.link.dto.LinkUpdateDTO;
import uz.dariko.response.Data;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("link")
public class LinkController extends AbstractController<LinkService> implements GenericCRUDController<LinkCreateDTO, LinkUpdateDTO, LinkDTO, UUID> {
    public LinkController(LinkService service) {
        super(service);
    }

    @Override
    public ResponseEntity<?> create(
            @RequestBody LinkCreateDTO DTO) throws Exception {
        return service.create(DTO);
    }

    @Override
    public ResponseEntity<?> update(LinkUpdateDTO DTO) {
        return service.update(DTO);
    }

    @Override
    public ResponseEntity<?> delete(UUID code) {
        return service.delete(code);
    }

    @Override
    public ResponseEntity<?> get(UUID code) {
        return service.get(code);
    }

    @Override
    public ResponseEntity<?> list() {
        return service.list();
    }

    @GetMapping("/list/{code}")
    public ResponseEntity<?> listWithType(@PathVariable Integer code){
        return service.listWithType(code);
    }
}
