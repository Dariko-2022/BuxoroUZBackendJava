package uz.dariko.collections.awards;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.dariko.base.entity.Auditable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StateAwards extends Auditable {
    @ManyToOne
    private AwardType awardType;

    private String uzBody;
    private String krBody;
    private String ruBody;

    private String imgUrl;


}
