package guacci.dealership.service;

import guacci.dealership.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService {
    @Autowired
    private WarehouseRepository warehouseRepository;
}
