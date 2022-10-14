package uz.dariko.base.service;

import org.springframework.http.ResponseEntity;
import uz.dariko.base.dto.BaseDTO;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.response.Data;

import java.io.Serializable;
import java.util.List;

public interface GenericCRUDService<CD extends BaseDTO, UD extends GenericDTO, GD extends GenericDTO,
        K extends Serializable> {
    ResponseEntity<?> create(CD DTO);

    ResponseEntity<?> update(UD DTO);

    ResponseEntity<?> delete(K key);

    ResponseEntity<?> get(K key);

    ResponseEntity<?> list();

}
