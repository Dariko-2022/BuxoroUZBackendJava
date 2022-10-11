package uz.dariko.base.mapper;

import uz.dariko.base.dto.BaseDTO;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.base.entity.BaseEntityID;

import java.util.List;

public interface AbstractMapper<CD extends BaseDTO, UD extends GenericDTO, D extends BaseDTO, E extends BaseEntityID> extends BaseMapper {

    D toDto(E entity);

    E fromCreateDto(CD createDto);

    E fromUpdateDto(UD updateDto);
    List<D> toDto(List<E> entities);
}
