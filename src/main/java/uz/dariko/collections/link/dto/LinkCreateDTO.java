package uz.dariko.collections.link.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.BaseDTO;
import uz.dariko.collections.file.File;

import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkCreateDTO implements BaseDTO {
    private String uzName;
    private String krName;
    private String ruName;

    private Integer linkTypeCode;

    private String imageID;

    private String url;


}
