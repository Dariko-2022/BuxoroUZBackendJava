package uz.dariko.base.service;

import org.springframework.http.ResponseEntity;
import uz.dariko.base.dto.BaseDTO;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.response.Data;

import java.io.Serializable;
import java.util.List;

public interface GenericCRUDService<CD extends BaseDTO, UD extends GenericDTO, GD extends GenericDTO, OD extends BaseDTO,
        K extends Serializable> {
    ResponseEntity<Data<GD>> create(CD DTO);

    ResponseEntity<Data<GD>> update(UD DTO);

    ResponseEntity<Data<Boolean>> delete(K key);

    ResponseEntity<Data<GD>> get(K key);

    ResponseEntity<Data<List<GD>>> list();

    ResponseEntity<Data<List<GD>>> listWithId(K key);

    ResponseEntity<Data<List<GD>>> changeOrder(OD od);
}
