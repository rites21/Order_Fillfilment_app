package order.fulfilment.app.Dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class StockRequest {
    private Long productId;
    private int quantity;
}
