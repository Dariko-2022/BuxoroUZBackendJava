package uz.dariko.collections.infoGroup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.checkerframework.checker.units.qual.UnknownUnits;
import uz.dariko.base.dto.GenericDTO;
import uz.dariko.collections.menu.Menu;

import javax.persistence.ManyToOne;
import java.util.UUID;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InfoGroupUpdateDTO extends GenericDTO {
    private String uzName;
    private String krName;
    private String ruName;

    private boolean visible;
}
