package corbos.fieldagent.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class SecurityClearance {

    @NotNull(message="Please make a selection")
    @Digits(integer=100, fraction=100, message="Please make a selection")
    @Id
    private int securityClearanceId;
    
    private String name;

}
