package uz.dariko.collections.link.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkUpdateDTO extends GenericDTO {
    private String uzName;
    private String krName;
    private String ruName;

    private Integer linkTypeCode;

    private String imageID;

    private String url;
}
