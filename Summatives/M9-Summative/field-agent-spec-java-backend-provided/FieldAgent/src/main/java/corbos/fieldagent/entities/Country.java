package corbos.fieldagent.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
public class Country {

    @NotNull(message="Please select a country.")
    @Id
    public String countryCode;
    
    @NotNull(message="Please select a country")
    public String name;

}
