package order.fulfilment.app.Repository;

import order.fulfilment.app.Models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse,Long> {

    @Query("SELECT w FROM Warehouse w JOIN w.stockItems s WHERE s.productId = :productId AND s.quantity >= :quantity ORDER BY SQRT(POWER(w.latitude - :latitude, 2) + POWER(w.longitude - :longitude, 2)) ASC")
    List<Warehouse> findNearestWarehouses(@Param("productId") Long productId,
                                          @Param("quantity") int quantity,
                                          @Param("latitude") double latitude,
                                          @Param("longitude") double longitude);
}

