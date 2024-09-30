package order.fulfilment.app.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "warehouses")
@Setter
@Getter
public class Warehouse extends Base{


    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double latitude; // Location latitude

    @Column(nullable = false)
    private double longitude; // Location longitude

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Stock> stockItems = new ArrayList<>();

    // Getters and setters
}
