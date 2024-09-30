package order.fulfilment.app.Controllers;

import order.fulfilment.app.Dtos.StockDTO;
import order.fulfilment.app.Dtos.StockRequest;
import order.fulfilment.app.Services.WarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/warehouses")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    // API to check stock for a product in a warehouse
    @GetMapping("/{warehouseId}/stock/{productId}")
    public ResponseEntity<StockDTO> checkStock(@PathVariable Long warehouseId, @PathVariable Long productId) {
        StockDTO stock = warehouseService.getStock(warehouseId, productId);
        return ResponseEntity.ok(stock);
    }

    // API to replenish stock for a product in a warehouse
    @PostMapping("/{warehouseId}/replenish")
    public ResponseEntity<Void> replenishStock(@PathVariable Long warehouseId, @RequestBody StockRequest request) {
        warehouseService.replenishStock(warehouseId, request.getProductId(), request.getQuantity());
        return ResponseEntity.ok().build();
    }
}
