package order.fulfilment.app.Repository;

import order.fulfilment.app.Models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
    Optional<Stock> findByProductIdAndWarehouseId(Long productId, Long warehouseId);
}
