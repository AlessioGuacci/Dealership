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
@Table(name = "Vehicles")

public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private double basePrice;

    @Column(nullable = false)
    private double baseRent;

    @Column(nullable = false)
    private boolean isAvailable;

    @OneToMany(mappedBy = "vehicle")
    private List<Sale>sales;

    @OneToMany(mappedBy = "vehicle")
    private List<Rent>rents;

    @ManyToOne
    @JoinColumn(name = "warehouseID")
    private Warehouse warehouse;
}
