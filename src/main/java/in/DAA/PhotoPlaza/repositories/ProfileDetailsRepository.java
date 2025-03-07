package in.DAA.PhotoPlaza.repositories;

import in.DAA.PhotoPlaza.entites.ProfileDetails;
import in.DAA.PhotoPlaza.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileDetailsRepository extends JpaRepository<ProfileDetails , Long> {
    Optional<ProfileDetails> findByUserId(Long userId);

    Optional<ProfileDetails> findByUser(User user);

}
