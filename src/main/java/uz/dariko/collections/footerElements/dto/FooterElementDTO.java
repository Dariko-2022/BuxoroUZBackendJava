package uz.dariko.collections.footerElements.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FooterElementDTO extends GenericDTO {
    private String body;

    private String uzRegulations;
    private String krRegulations;
    private String ruRegulations;

    private int type;
}
