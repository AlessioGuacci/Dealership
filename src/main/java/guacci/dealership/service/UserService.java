package guacci.dealership.service;

import guacci.dealership.repository.RentRepository;
import guacci.dealership.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


}
