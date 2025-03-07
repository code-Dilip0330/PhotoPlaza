package in.DAA.PhotoPlaza.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date uploadDate;
    private long price;
//    private byte[] image;
    private MultipartFile image; // For file upload
    private byte[] imageBytes;   // For storing byte[] data
    private String imageBase64;  // For displaying image in HTML
    private String keywords;
//    To specify the user
    @NotNull(message = "User Id must not be null")
    private Long userId;
    private ProfileDetailsDto profileDetails; //Add to include profileDetails
}
