package guacci.dealership.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VehicleDTO {
    private String brand;

    private String model;

    private int year;

    private double price;

    private boolean isAvailable;


}
