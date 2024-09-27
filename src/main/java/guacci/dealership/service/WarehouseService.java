package guacci.dealership.service;

import guacci.dealership.DTO.VehicleDTO;
import guacci.dealership.model.Vehicle;
import guacci.dealership.model.Warehouse;
import guacci.dealership.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;

    public Warehouse createWarehouse(){
        Warehouse warehouse = new Warehouse();
        return warehouseRepository.save(warehouse);
    }

    public List<VehicleDTO> getAllWarehouseInventory(Long id){
        Warehouse warehouse=warehouseRepository.findById(id).orElseThrow(()->new RuntimeException("Warehouse Not found"));
        List<Vehicle>vehicles=warehouse.getWarehouseVehicles();
        List<VehicleDTO>vehicleDTOS=vehicles.
                stream().map(vehicle -> new VehicleDTO(
                        vehicle.getBrand(),
                        vehicle.getModel(),
                        vehicle.getYear(),
                        vehicle.getPrice(),
                        vehicle.isAvailable()
                )).collect(Collectors.toList());
        return vehicleDTOS;
    }

    public void removeFromWarehouse (Vehicle vehicle){
        Warehouse warehouse = vehicle.getWarehouse();
        if(warehouse==null){
            throw new RuntimeException("Warehouse does not exist");
        }
        warehouse.getWarehouseVehicles().remove(vehicle);
        warehouseRepository.save(warehouse);
    }

    public void addToWarehouse(Vehicle vehicle){
        Warehouse warehouse = vehicle.getWarehouse();
        if(warehouse==null){
            throw new RuntimeException("Warehouse does not exist");
        }
        warehouse.getWarehouseVehicles().add(vehicle);
        warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(long id){
        warehouseRepository.deleteById(id);
    }
}
