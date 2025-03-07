package in.DAA.PhotoPlaza.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDetailsDto {
    private Long id;
    private String bio;
    private MultipartFile image; // For file upload
    private byte[] imageBytes;   // For storing byte[] data
    private String imageBase64;  // For displaying image in HTML

    //    To specify the user
    @NotNull(message = "User Id must not be null")
    private Long userId;
    private UserDto user;
}
