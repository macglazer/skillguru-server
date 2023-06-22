package skillguru.model.request.zencal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZencalProfileCreateRequest {

    private String zencalId;

    private UUID uuid;

    private String firstName;

    private String lastName;

    private String email;

    //TODO password string variable is temporary ofc
    private String password;
}
