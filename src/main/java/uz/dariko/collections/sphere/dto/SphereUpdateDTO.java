package uz.dariko.collections.sphere.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SphereUpdateDTO extends GenericDTO {
    private String uzName;
    private String krName;
    private String ruName;
}