package in.DAA.PhotoPlaza.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String contact;
    private String email;
    private String password;

    @Column(unique = true, nullable = false)
    private String userName;

    private LocalDate registrationDate;

    @ManyToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
    joinColumns =  @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Product> products = new HashSet<>();

//    // Add this to your existing User entity
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
//    private in.DAA.PhotoPlaza.entities.BusinessAccount businessAccount;
}
