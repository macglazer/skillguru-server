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
public class RegisterMentorRequest {

    private String firstName;

    private String lastName;

    private int age;

    private String email;
}