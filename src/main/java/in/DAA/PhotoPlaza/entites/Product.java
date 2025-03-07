package in.DAA.PhotoPlaza.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="Products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String category;
    private Date uploadDate;
    private long price;
    @Lob
    private byte[] image;
    private String keywords;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
