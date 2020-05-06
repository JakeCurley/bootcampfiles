package corbos.fieldagent.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Agent {
    
    @NotBlank(message = "Identifier must not be blank.")
    @Size(max = 25, message = "Identifier must be 25 characters or less.")
    @Id
    private String identifier;
    
    @Pattern(regexp="^[a-zA-Z]+$", message="Please enter a valid name.")
    @NotBlank(message = "First Name must not be blank.")
    @Size(max = 25, message = "First name must be 25 characters or less.")
    private String firstName;
    
    @Size(max = 25, message = "Middle name must be 25 characters or less.")
    private String middleName;
    
    @Pattern(regexp="^[a-zA-Z]+$", message="Please enter a valid name.")
    @NotBlank(message = "Last Name must not be blank.")
    @Size(max = 25, message = "Last name must be 25 characters or less.")
    private String lastName;
    
    @URL(message="Please enter a valid URL.")
    @Size(max = 255, message = "Picture URL must be 255 characters or less.")
    private String pictureUrl;
    
    @NotNull(message = "Birth Date must not be blank.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    
    
    @Min(value = 36, message="Height must be more than 36.")
    @Max(value = 96, message="Height must be less than 96.")
    @NotNull(message = "Height must not be blank.")
    private int height;
    
    @NotNull(message = "Activation Date must not be blank.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate activationDate;
    
    public boolean isActive;
    
    @NotNull(message = "Please select an agency.")
    @ManyToOne
    @JoinColumn(name = "agency_id")
    private Agency agency;

    
    @NotNull(message = "Please select a security clearance.")
    @ManyToOne
    @JoinColumn(name = "security_clearance_id")
    private SecurityClearance securityClearance;

}
