package uz.dariko.collections.infoGroup.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InfoGroupCreateDTO implements BaseDTO {
    private String uzName;
    private String krName;
    private String ruName;
}
