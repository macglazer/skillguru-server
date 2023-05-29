package skillguru.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@ToString
@Entity(name = "mentor")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mentor {
    @Id
    private Long mentorID;


}
