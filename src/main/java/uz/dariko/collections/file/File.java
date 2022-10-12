package uz.dariko.collections.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.dariko.base.entity.Auditable;


import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class File extends Auditable {
    private String filePath;
    private String originalName;
    private String extention;
    private String generatedName;
    private Long size;
    private Boolean isActive = true;
    private Integer orderNumber;
}
