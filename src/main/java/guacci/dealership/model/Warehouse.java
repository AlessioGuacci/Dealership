package guacci.dealership.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Warehouse")
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private int capacity;

    @Column
    private int currentStock;

    @OneToMany(mappedBy = "warehouse")
    private List<Vehicle>warehouseVehicles;

    @OneToMany(mappedBy = "warehouse")
    private List<Sale>sales;

    @OneToMany(mappedBy = "warehouse")
    private List<Rent>rents;

}
