package guacci.dealership.service;

import guacci.dealership.model.Rent;
import guacci.dealership.model.User;
import guacci.dealership.model.Vehicle;
import guacci.dealership.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Objects;


@Service
public class RentService {
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WarehouseService warehouseService;

    @Transactional
    @PreAuthorize("hasRole('ADMIN')or hasRole('EMPLOYEE')")
    public Rent createARental(Long customerID, Long vehicleID, Long employeeID,
                              LocalDate startDate, LocalDate endDate){
        Vehicle vehicle= vehicleRepository.findById(vehicleID).
                orElseThrow(()->new RuntimeException("Vehicle does not exist"));

        if(!vehicle.isAvailable()){
            throw new RuntimeException("Vehicle is not available at the moment");
        }
        if (startDate.isBefore(LocalDate.now())){
            throw new RuntimeException("Rental start date is invalid");
        }
        if(endDate.isBefore(startDate)){
            throw new RuntimeException("Rental end date is invalid");
        }

        User employee = userRepository.findById(employeeID).orElseThrow(()
                ->new RuntimeException("Employee not found"));

        User customer= userRepository.findById(customerID).orElseThrow(()->
                new RuntimeException("Customer does not exist"));
        Rent rent= new Rent();
        rent.setRenter(customer);
        rent.setVendor(employee);
        rent.setVehicle(vehicle);
        rent.setRentalStartDate(startDate);
        rent.setRentalEndDate(endDate);

        vehicle.setAvailable(false);
        vehicleRepository.save(vehicle);
        warehouseService.removeFromWarehouse(vehicle);

        return rentRepository.save(rent);

    }

    @PreAuthorize("hasRole('ADMIN')or hasRole('EMPLOYEE')")
    public Rent selectRental(Long id){
        return rentRepository.findById(id).orElseThrow(()->
                new RuntimeException("Rental does not exist"));
    }

    @PreAuthorize("hasRole('ADMIN')or hasRole('EMPLOYEE')")
    public Rent updateRental(Long rentID,Rent rental){
        Rent rent= rentRepository.findById(rentID).orElseThrow(()->
                new RuntimeException("Rental does not exist"));
        rent.setRentPrice(rental.getRentPrice());
        rent.setVendor(rental.getVendor());
        rent.setVehicle(rental.getVehicle());
        rent.setRenter(rental.getRenter());
        rent.setVendor(rental.getVendor());
        rent.setRentalStartDate(rental.getRentalStartDate());
        rent.setRentalEndDate(rental.getRentalEndDate());
        rent.setWarehouse(rental.getWarehouse());
        return rentRepository.save(rent);

    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteRental(Long id){
        rentRepository.deleteById(id);
    }

    @PreAuthorize("hasRole('ADMIN')or hasRole('EMPLOYEE')")
    public void endRental(Rent rent){
        if(Objects.equals(rent.getRentalEndDate(), LocalDate.now())){
            warehouseService.addToWarehouse(rent.getVehicle());
            Vehicle vehicle = rent.getVehicle();
            vehicle.setAvailable(true);
            vehicleRepository.save(vehicle);
            rentRepository.deleteById(rent.getId());

        } else {
           throw new RuntimeException("Rental is still valid");
        }
    }
}
