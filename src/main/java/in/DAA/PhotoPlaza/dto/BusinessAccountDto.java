package in.DAA.PhotoPlaza.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessAccountDto {
    private Long id;
    private String businessName;
    private String businessType;
    private String websiteUrl;

    //    To specify the user
    @NotNull(message = "User Id must not be null")
    private Long userId;

}