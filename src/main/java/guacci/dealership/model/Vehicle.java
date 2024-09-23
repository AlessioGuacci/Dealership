package guacci.dealership.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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
    private double price;

    @Column(nullable = false)
    private boolean isAvailable;

    @OneToMany(mappedBy = "vehicle")
    private Sale sale;

    @OneToMany(mappedBy = "vehicle")
    private Rent rent;

    @ManyToOne
    @JoinColumn(name = "warehouseID")
    private Warehouse warehouse;
}
