package guacci.dealership.service;

import guacci.dealership.model.Sale;
import guacci.dealership.model.User;
import guacci.dealership.model.Vehicle;
import guacci.dealership.model.Warehouse;
import guacci.dealership.repository.SaleRepository;
import guacci.dealership.repository.UserRepository;
import guacci.dealership.repository.VehicleRepository;
import guacci.dealership.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Sale createSale(Long customerID, Long employeeID, Long vehicleID, Long warehouseID){
        Vehicle vehicle = vehicleRepository.findById(vehicleID).orElseThrow(()->
                new RuntimeException("Vehicle does not exist"));
        if(!vehicle.isAvailable()){
            throw new RuntimeException("Vehicle is not available");
        }
        User customer= userRepository.findById(customerID).orElseThrow(()->
                new RuntimeException("Customer does not exist"));

        User employee = userRepository.findById(employeeID).orElseThrow(()->
                new RuntimeException("Employee not found"));

        Warehouse warehouse = warehouseRepository.findById(warehouseID).orElseThrow(()->
                new RuntimeException("Warehouse not found"));

        Sale sale = new Sale();
        sale.setVehicle(vehicle);
        sale.setBuyer(customer);
        sale.setSeller(employee);
        sale.setSaleDate(LocalDate.now());
        sale.setWarehouse(warehouse);
        return saleRepository.save(sale);
    }

    public Sale selectSale(Long id){
      return saleRepository.findById(id).orElseThrow(()->
              new RuntimeException("Sale does not exist"));
    }

    public Sale updateSale(Long id,Sale updatedSale){
        Sale sale=saleRepository.findById(id).orElseThrow(()->
                new RuntimeException("Sale does not exist"));
        sale.setWarehouse(updatedSale.getWarehouse());
        sale.setVehicle(updatedSale.getVehicle());
        sale.setBuyer(updatedSale.getBuyer());
        sale.setSeller(updatedSale.getSeller());
        sale.setSaleDate(updatedSale.getSaleDate());
        return saleRepository.save(sale);
    }

    public void deleteSale(Long id){
        saleRepository.deleteById(id);
    }
}
