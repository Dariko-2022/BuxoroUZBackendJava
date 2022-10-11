package uz.dariko.base.service;


import uz.dariko.base.repository.BaseRepository;
import uz.dariko.base.validator.BaseValidator;

public class AbstractService<R extends BaseRepository, V extends BaseValidator> implements BaseService {
    public R repository;
    public V validator;

    public AbstractService(R repository, V validator) {
        this.repository = repository;
        this.validator = validator;
    }
}
