package dto.ingredients;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class IngredientsResponseBody {
    public String success;
    public List<Ingredient> data;
}
