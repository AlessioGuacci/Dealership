package guacci.dealership.service;

import guacci.dealership.model.Rent;
import guacci.dealership.model.User;
import guacci.dealership.model.Vehicle;
import guacci.dealership.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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

//    public Rent createARental(Long customerID, Long vehicleID, Long employeeID,
//                              Date startDate, Date endDate){
//        Vehicle vehicle= vehicleRepository.findById(vehicleID).
//                orElseThrow(()->new RuntimeException("Vehicle does not exist"));
//
//        if(!vehicle.isAvailable()){
//            throw new RuntimeException("Vehicle is not available at the moment");
//        }
//        User custumer=UserRepository.
//    }
}
