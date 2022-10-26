package uz.dariko.collections.link;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;
import uz.dariko.collections.file.File;

import javax.persistence.Entity;
import javax.persistence.OneToOne;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Link extends Auditable {
    private String uzName;
    private String krName;
    private String ruName;

    private Integer linkTypeCode;

    @OneToOne
    private File image;

    private String url;


}
