package uz.dariko.base.controller;

import uz.dariko.base.service.BaseService;

public abstract class AbstractController<S extends BaseService> {
    public S service;

    public AbstractController(S service) {
        this.service = service;
    }
}

