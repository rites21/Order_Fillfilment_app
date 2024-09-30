package order.fulfilment.app.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "stock")
@Setter
@Getter
@AllArgsConstructor
//@NoArgsConstructor
public class Stock extends Base{

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouse warehouse;



    public Stock(Long productId, Long warehouseId, int quantity) {
        this.productId = productId;
        this.warehouse = new Warehouse();
        this.warehouse.setId(warehouseId);
        this.quantity = quantity;
    }

    public Stock() {}
}

