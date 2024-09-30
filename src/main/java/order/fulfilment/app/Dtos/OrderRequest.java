package order.fulfilment.app.Dtos;

import lombok.Getter;
import lombok.Setter;
import order.fulfilment.app.Models.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Setter
public class OrderRequest {
    private List<OrderItem> orderItems;
    private double latitude;
    private double longitude;
}
