package skillguru.model.request.zencal;

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
public class ZencalProfileCreateRequest {

    private String firstName;

    private String lastName;

    private String email;

    //TODO password string variable is temporary ofc
    private String password;
}
