package uz.dariko.collections.person;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.dariko.base.controller.AbstractController;

@RestController
@RequestMapping("/person")
public class PersonController extends AbstractController<PersonService> {
    public PersonController(PersonService service) {
        super(service);
    }
}
