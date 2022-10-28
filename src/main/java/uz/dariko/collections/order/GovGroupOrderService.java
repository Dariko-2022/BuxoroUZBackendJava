package uz.dariko.collections.order;

import org.springframework.stereotype.Service;
import uz.dariko.collections.order.dto.GovGroupOrderCreateDTO;
import uz.dariko.collections.order.dto.GovGroupOrderUpdateDTO;
import uz.dariko.utils.EntityGetter;

import java.util.ArrayList;
import java.util.List;

@Service
public class GovGroupOrderService {
    private final GovGroupOrderRepository repository;
    private final EntityGetter entityGetter;
    private final GovGroupOrderMapper mapper;

    public GovGroupOrderService(GovGroupOrderRepository repository, EntityGetter entityGetter, GovGroupOrderMapper mapper) {
        this.repository = repository;
        this.entityGetter = entityGetter;
        this.mapper = mapper;
    }

    public GovGroupOrder create(GovGroupOrderCreateDTO dto) {
        GovGroupOrder govGroupOrder = new GovGroupOrder();
        govGroupOrder.setOrder(dto.getOrder());
        govGroupOrder.setT(entityGetter.getStateEmployee(dto.getStateEmployeeId()));
        return repository.save(govGroupOrder);
    }

    public List<GovGroupOrder> create(List<GovGroupOrderCreateDTO> list) {
        List<GovGroupOrder> res = new ArrayList<>();
        for(GovGroupOrderCreateDTO g : list) {
            res.add(create(g));
        }
        return res;
    }

    public GovGroupOrder update(GovGroupOrderUpdateDTO dto) {
        if(dto.getId() != null) {
            GovGroupOrder govGroupOrder = entityGetter.getGovGroupOrder(dto.getId());
            govGroupOrder.setOrder(dto.getOrder());
            govGroupOrder.setT(entityGetter.getStateEmployee(dto.getStateEmployeeId()));
            return repository.save(govGroupOrder);
        }
        return create(mapper.fromUpdateDtoToCreateDto(dto));
    }

    public List<GovGroupOrder> update(List<GovGroupOrderUpdateDTO> list) {
        List<GovGroupOrder> res = new ArrayList<>();
        for(GovGroupOrderUpdateDTO g : list) {
            res.add(update(g));
        }
        return res;
    }
}
