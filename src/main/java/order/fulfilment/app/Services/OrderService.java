package order.fulfilment.app.Services;

import order.fulfilment.app.Dtos.OrderRequest;
import order.fulfilment.app.Exceptions.InsufficientStockException;
import order.fulfilment.app.Exceptions.NoWarehouseAvailableException;
import order.fulfilment.app.Exceptions.OrderNotFoundException;
import order.fulfilment.app.Exceptions.StockNotFoundException;
import order.fulfilment.app.Models.Order;
import order.fulfilment.app.Models.OrderItem;
import order.fulfilment.app.Models.Stock;
import order.fulfilment.app.Models.Warehouse;
import order.fulfilment.app.Repository.OrderRepository;
import order.fulfilment.app.Repository.StockRepository;
import order.fulfilment.app.Repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private StockRepository stockRepository;
    public Order createOrder(OrderRequest request) {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Pending");
        for (OrderItem item : request.getOrderItems()) {
            List<Warehouse> availableWarehouses = warehouseRepository.findNearestWarehouses(
                    item.getProductId(), item.getQuantity(), request.getLatitude(), request.getLongitude());

            if (!availableWarehouses.isEmpty()) {
                Warehouse selectedWarehouse = availableWarehouses.get(0);
                Stock stock = stockRepository.findByProductIdAndWarehouseId(item.getProductId(), selectedWarehouse.getId())
                        .orElseThrow(() -> new StockNotFoundException("No Stock avilable"));

                if (stock.getQuantity() >= item.getQuantity()) {
                    stock.setQuantity(stock.getQuantity() - item.getQuantity());
                    stockRepository.save(stock);
                    order.addOrderItem(item);
                } else {
                    throw  new StockNotFoundException("No Stock avilable");
                }
            } else {
                throw new NoWarehouseAvailableException("No warehouse has enough stock for this item");
            }
        }

        return orderRepository.save(order);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order with ID " + id + " not found."));
    }
}
