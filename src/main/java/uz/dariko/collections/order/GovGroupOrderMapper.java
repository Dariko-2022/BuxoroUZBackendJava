package uz.dariko.collections.order;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import uz.dariko.base.mapper.AbstractMapper;
import uz.dariko.collections.order.dto.GovGroupOrderCreateDTO;
import uz.dariko.collections.order.dto.GovGroupOrderDTO;
import uz.dariko.collections.order.dto.GovGroupOrderUpdateDTO;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface GovGroupOrderMapper extends AbstractMapper<GovGroupOrderCreateDTO, GovGroupOrderUpdateDTO, GovGroupOrderDTO, GovGroupOrder> {



    default GovGroupOrderCreateDTO fromUpdateDtoToCreateDto(GovGroupOrderUpdateDTO dto) {
        GovGroupOrderCreateDTO res = new GovGroupOrderCreateDTO();
        res.setOrder(dto.getOrder());
        res.setStateEmployeeId(dto.getStateEmployeeId());
        return res;
    }
}
