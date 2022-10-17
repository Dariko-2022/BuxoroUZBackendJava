package uz.dariko.collections.govSphere.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GovSphereUpdateDTO extends GenericDTO {
    private String uzName;
    private String krName;
    private String ruName;
}
