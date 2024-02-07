package dto.orders;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestBody {
    private List<String> ingredients;
}
