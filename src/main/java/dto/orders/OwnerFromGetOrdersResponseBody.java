package dto.orders;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OwnerFromGetOrdersResponseBody {
    private String name;
    private String emale;
    private String createdAt;
    private String updatedAt;

}
