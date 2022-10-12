package uz.dariko.collections.person.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonUpdateDTO extends GenericDTO {

    private String name;
    private String job;
    private int age;

}
