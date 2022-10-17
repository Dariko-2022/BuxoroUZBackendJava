package uz.dariko.collections.govSphere.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GovSphereCreateDTO implements BaseDTO {
    private String uzName;
    private String krName;
    private String ruName;
}
