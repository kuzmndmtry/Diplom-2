package dto.ingredients;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
public class Ingredient {
    String _id;
    String name;
    String type;
    String proteins;
    String fat;
    String carbohydrates;
    String calories;
    String price;
    String image;
    String image_mobile;
    String image_large;
    String __v;
}
