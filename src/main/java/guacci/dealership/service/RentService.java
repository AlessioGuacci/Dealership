package guacci.dealership.service;

import guacci.dealership.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RentService {
    @Autowired
   private SaleRepository saleRepository;
}
