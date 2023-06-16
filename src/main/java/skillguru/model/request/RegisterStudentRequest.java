package skillguru.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterStudentRequest {

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