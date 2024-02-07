package dto.user;

import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequestBody {
    private String email = RandomStringUtils.random(10, true, true) + "@yandex.ru";
    private String name = RandomStringUtils.random(10);
}
