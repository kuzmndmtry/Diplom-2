package dto.orders;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseBody {
    private String success;
    private String numa;
    private OrderFromGetOrdersResponseBody order;
}
