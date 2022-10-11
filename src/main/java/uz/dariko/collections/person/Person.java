package uz.dariko.collections.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Person extends Auditable {

    private String name;
    private String job;
    private int age;

}
