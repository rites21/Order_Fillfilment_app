package order.fulfilment.app.Services;

import jakarta.transaction.Transactional;
import order.fulfilment.app.Dtos.StockDTO;
import order.fulfilment.app.Exceptions.StockNotFoundException;
import order.fulfilment.app.Models.Stock;
import order.fulfilment.app.Repository.StockRepository;
import order.fulfilment.app.Repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private StockRepository stockRepository;


    public StockDTO getStock(Long warehouseId, Long productId) {
        Stock stock = stockRepository.findByProductIdAndWarehouseId(productId, warehouseId)
                .orElseThrow(() -> new StockNotFoundException("Stock for product " + productId + " not found in warehouse " + warehouseId));

        return new StockDTO(stock.getProductId(), stock.getQuantity());
    }


    @Transactional
    public void replenishStock(Long warehouseId, Long productId, int quantity) {
        Stock stock = stockRepository.findByProductIdAndWarehouseId(productId, warehouseId)
                .orElse(new Stock(productId, warehouseId, 0));

        stock.setQuantity(stock.getQuantity() + quantity);
        stockRepository.save(stock);
    }
}
