package uz.dariko.base.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.BaseEntityID;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order<T> extends BaseEntityID {

    private int order;
    private T t;

}
