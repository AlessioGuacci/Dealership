package guacci.dealership.service;

import guacci.dealership.DTO.UserDTO;
import guacci.dealership.model.User;
import guacci.dealership.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private UserDTO convertEntityIntoDTO(User user){
        return new UserDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole());
    }

    private User convertUserDTOToUSer(UserDTO userDTO){
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setRole(userDTO.getRole());
        return user;
    }

    public User createUser(User user){
        return userRepository.save(user);
    }

    public User selectUser(User user){
        return userRepository.findById(user.getId()).orElseThrow(()->
                new RuntimeException("User does not exist"));
    }

    public User updateUser(User user){
        User userToUpdate= userRepository.findById(user.getId()).orElseThrow(()->
                new RuntimeException("User does not exist"));
        userToUpdate.setId(user.getId());
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setRole(user.getRole());

        if (user.getPassword()!= null && !user.getPassword().isEmpty()){
            userToUpdate.setPassword(user.getPassword());
        }

        return userRepository.save(userToUpdate);
    }

    public void deleteUser(Long id){
        User user= userRepository.findById(id).orElseThrow(()->
                new RuntimeException("User does not exist"));
        userRepository.delete(user);

    }


}
