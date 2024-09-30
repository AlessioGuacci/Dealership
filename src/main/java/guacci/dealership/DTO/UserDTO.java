package guacci.dealership.DTO;

import guacci.dealership.model.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private String firstName;

    private String lastName;

    private String email;

    private RoleType role;


}
