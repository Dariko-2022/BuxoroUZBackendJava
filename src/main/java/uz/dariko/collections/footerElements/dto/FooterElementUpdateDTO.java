package uz.dariko.collections.footerElements.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FooterElementUpdateDTO extends GenericDTO {
    private String uzBody;
    private String krBody;
    private String ruBody;

    private String uzRegulations;
    private String krRegulations;
    private String ruRegulations;

    @Column(unique = true)
    private int type;
}
