package uz.dariko.collections.file;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;

import javax.persistence.Entity;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class File extends Auditable {

    private String filePath;
    private String originalName;
    private String generatedName;
    private String extention;
    private Long size;
    private Boolean isActive = true;
    private Integer orderNumber;

}
