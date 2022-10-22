package uz.dariko.collections.subGovGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.dto.GenericDTO;

import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubGovGroupUpdateDTO extends GenericDTO {
    private UUID id;
    private String uzName;
    private String ruName;
    private String krName;
    private String uzDescription;
    private String ruDescription;
    private String krDescription;

    private UUID govGroupId;

    private int rank;
    private boolean visible;
}
