package uz.dariko.collections.footerElements;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.dariko.base.controller.AbstractController;
import uz.dariko.collections.footerElements.dto.FooterElementCreateDTO;

@RestController
@RequestMapping("footerElement")
public class FooterElementController extends AbstractController<FooterElementService>  {

    public FooterElementController(FooterElementService service) {
        super(service);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody  FooterElementCreateDTO DTO) throws Exception {
        return service.create(DTO);
    }

    @GetMapping("/byType")
    public ResponseEntity<?> list(@RequestParam String code) {
        return service.getByType(code);
    }


    @GetMapping("/list")
    public ResponseEntity<?> list() {
        return service.getList();
    }
}
