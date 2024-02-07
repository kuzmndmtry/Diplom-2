package dto.ingredients;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class IngredientList {
    private List<String> ingredients;
}
