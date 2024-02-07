package dto.ingredients;



import java.util.List;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientListFromGetOrdersResponseBody {
    private List<String> ingredients;
}
