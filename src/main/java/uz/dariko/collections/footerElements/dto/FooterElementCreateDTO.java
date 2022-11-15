package uz.dariko.collections.footerElements.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import uz.dariko.base.dto.BaseDTO;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FooterElementCreateDTO implements BaseDTO {
    private String body;

    private String uzRegulations;
    private String krRegulations;
    private String ruRegulations;

    private int type;
}
