package uz.dariko.collections.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import uz.dariko.base.entity.BaseEntityID;
import uz.dariko.base.entity.Order;
import uz.dariko.collections.stateEmloyee.StateEmployee;

import javax.persistence.Entity;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@Entity
public class GovGroupOrder extends Order<StateEmployee>  {


}
