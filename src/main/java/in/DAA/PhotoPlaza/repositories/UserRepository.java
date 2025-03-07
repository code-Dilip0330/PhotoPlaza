package in.DAA.PhotoPlaza.repositories;

import in.DAA.PhotoPlaza.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailIgnoreCase(String email);
    Optional<User> findByUserName(String userName);
    Optional<User> findByUserNameOrEmail(String userName, String email);
    Optional<User> findByUserNameIgnoreCaseOrEmailIgnoreCase(String userName, String email);

}

