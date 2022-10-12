package uz.dariko.base.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import uz.dariko.base.dto.BaseDTO;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.response.Data;
import uz.dariko.utils.BaseUtils;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;

public interface GenericCRUDController<CD extends BaseDTO, UD extends GenericDTO, GD extends GenericDTO, OD extends BaseDTO,  K extends Serializable> {
    @RequestMapping(value = BaseUtils.CREATE_PATH, method = RequestMethod.POST)
    ResponseEntity<Data<GD>> create(@Valid @RequestBody CD DTO);

    @RequestMapping(value = BaseUtils.UPDATE_PATH, method = RequestMethod.PATCH)
    ResponseEntity<Data<GD>> update(@Valid @RequestBody UD DTO);

    @RequestMapping(value = BaseUtils.DELETE_PATH, method = RequestMethod.DELETE)
    ResponseEntity<Data<Boolean>> delete(@PathVariable K code);

    @RequestMapping(value = BaseUtils.GET_PATH, method = RequestMethod.GET)
    ResponseEntity<Data<GD>> get(@PathVariable K code);

    @RequestMapping(value = BaseUtils.LIST_PATH, method = RequestMethod.GET)
    ResponseEntity<Data<List<GD>>> list();

    @RequestMapping(value = BaseUtils.LIST_WITH_ID_PATH, method = RequestMethod.GET)
    ResponseEntity<Data<List<GD>>> listWithId(@PathVariable K code);

    @RequestMapping(value = BaseUtils.CHANGE_ORDER_PATH, method = RequestMethod.PATCH)
    ResponseEntity<Data<List<GD>>>  changeOrder(@RequestBody OD orderDTO);
}