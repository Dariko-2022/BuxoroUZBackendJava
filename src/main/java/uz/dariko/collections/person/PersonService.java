package uz.dariko.collections.person;

import org.springframework.stereotype.Service;
import uz.dariko.base.service.AbstractService;

@Service
public class PersonService extends AbstractService<PersonRepository,PersonValidator>{
    public PersonService(PersonRepository repository, PersonValidator validator) {
        super(repository, validator);
    }
}
