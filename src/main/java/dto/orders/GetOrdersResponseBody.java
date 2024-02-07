package dto.orders;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetOrdersResponseBody {
    String success;
    OrderFromGetOrdersResponseBody orders;
    String total;
    String totalToday;
}
