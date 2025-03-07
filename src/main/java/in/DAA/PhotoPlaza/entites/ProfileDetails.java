package in.DAA.PhotoPlaza.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profile_details")
public class ProfileDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String bio;
    @Lob
    private byte[] image;

    @OneToOne
    @JoinColumn(name = "user_id",unique = true, referencedColumnName = "id")
    private User user;
}
