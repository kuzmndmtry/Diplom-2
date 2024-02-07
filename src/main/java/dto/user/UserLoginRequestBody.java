package dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginRequestBody {
    private String email;
    private String password;
    public UserLoginRequestBody() {
    }
}
