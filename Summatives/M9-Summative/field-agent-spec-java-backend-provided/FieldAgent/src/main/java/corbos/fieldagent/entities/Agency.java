package corbos.fieldagent.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Agency {

    @Pattern(regexp="^([1-9]+[0-9]* | [1-9])$")
    @Id
    private int agencyId;
    private String name;

}
