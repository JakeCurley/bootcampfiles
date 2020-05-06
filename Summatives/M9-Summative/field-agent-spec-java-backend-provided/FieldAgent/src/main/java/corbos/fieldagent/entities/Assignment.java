package corbos.fieldagent.entities;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int assignmentId;
    
    @NotNull(message = "Start date must not be blank.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    
    @NotNull(message = "Projected end date must not be blank.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate projectedEndDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate actualEndDate;
    
    private String notes;

    @NotNull(message = "Please select a country.")
    @ManyToOne
    @JoinColumn(name = "country_code")
    private Country country;

    @NotNull(message = "Agent must not be blank.")
    @ManyToOne
    @JoinColumn(name = "identifier")
    private Agent agent;

}
