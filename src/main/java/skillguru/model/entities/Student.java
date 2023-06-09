package skillguru.model.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@ToString
@Entity(name = "student")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentID;

    private String firstName;

    private String lastName;

    private String email;

    private String jobTitle;

    private String linkedinURL;

    private String goalDescription;

    private String preferTimeZone;

    private boolean emailPreferences;

    private boolean accountActivate;
}