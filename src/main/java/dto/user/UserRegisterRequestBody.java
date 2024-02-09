package dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;

@Getter
@Setter
@AllArgsConstructor
public class UserRegisterRequestBody {
    private String email = RandomStringUtils.random(10, true, true) + "@yandex.ru";
    private String password = "P@ssw0rd" + RandomStringUtils.random(4, false, true);
    private String name = RandomStringUtils.random(10);
    public UserRegisterRequestBody() {
    }
}
