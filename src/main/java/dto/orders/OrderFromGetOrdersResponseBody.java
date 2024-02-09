package dto.orders;

import dto.ingredients.IngredientListFromGetOrdersResponseBody;

import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderFromGetOrdersResponseBody {
    private String _id;
    private List<IngredientListFromGetOrdersResponseBody> ingredients;
    public OwnerFromGetOrdersResponseBody owner;
    private String status;
    private String number;
    private String createdAt;
    private String updatedAt;
    private String price;
}
