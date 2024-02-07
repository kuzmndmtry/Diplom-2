package dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginResponseBody {
    private String success;
    private User user;
    private String accessToken;
    private String refreshToken;
}
