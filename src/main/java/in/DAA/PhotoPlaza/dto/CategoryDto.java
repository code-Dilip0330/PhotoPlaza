package in.DAA.PhotoPlaza.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CategoryDto {
    private Long id;
    private String categoryName;
    private String status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

}
