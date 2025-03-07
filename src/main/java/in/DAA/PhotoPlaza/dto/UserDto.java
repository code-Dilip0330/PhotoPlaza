package in.DAA.PhotoPlaza.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    @NotEmpty(message = "First name is required")
    @Pattern(regexp = "^[A-Za-z]{2,30}$", message = "First name must contain only letters and be 2-30 characters long")
    private String firstName;
    @NotEmpty(message = "Last name is required")
    @Pattern(regexp = "^[A-Za-z]{2,30}$", message = "Last name must contain only letters and be 2-30 characters long")
    private String lastName;
    @NotEmpty(message = "Contact number is required")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Contact number must be 10-15 digits long")
    private String contact;
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Invalid email format")
    private String email;
    @NotEmpty(message = "Password should not be empty")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "Password must have at least 8 characters, one uppercase, one lowercase, one number, and one special character")
    private String password;
    private String role; // USER, ADMIN, BLOCKED, ARCHIVED

}
