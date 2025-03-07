package in.DAA.PhotoPlaza.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {
    @NotEmpty(message = "Username or Email cannot be empty")
    private String userNameOrEmail;

    @NotEmpty(message = "Password cannot be empty")
    private String password;
}