package guacci.dealership.service;

import guacci.dealership.DTO.VehicleDTO;
import guacci.dealership.model.Vehicle;
import guacci.dealership.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

   private Vehicle convertDTOToEntity (VehicleDTO vehicleDTO){
       Vehicle vehicle = new Vehicle();
       vehicle.setBrand(vehicleDTO.getBrand());
       vehicle.setModel(vehicleDTO.getModel());
       vehicle.setPrice(vehicleDTO.getPrice());
       vehicle.setYear(vehicleDTO.getYear());
       vehicleDTO.setAvailable(vehicleDTO.isAvailable());
       return vehicle;
   }

   private VehicleDTO convertEntityToDTO(Vehicle vehicle){
       return new VehicleDTO(
               vehicle.getBrand(),
               vehicle.getModel(),
               vehicle.getYear(),
               vehicle.getPrice(),
               vehicle.isAvailable());
   }

   public List<VehicleDTO>getAllVehicles(){

       List<Vehicle>vehicleList=vehicleRepository.findAll();
       return vehicleList.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
   }

    public VehicleDTO selectVehicle(Long id){
       Vehicle vehicle = vehicleRepository.findById(id).
               orElseThrow(()-> new RuntimeException("Vehicle not found"));
       return convertEntityToDTO(vehicle);
    }

    public Vehicle createVehicle(Vehicle vehicle){
       return vehicleRepository.save(vehicle);
    }
}
