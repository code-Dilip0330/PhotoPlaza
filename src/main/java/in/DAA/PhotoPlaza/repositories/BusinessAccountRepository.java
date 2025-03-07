package in.DAA.PhotoPlaza.repositories;

import in.DAA.PhotoPlaza.entites.BusinessAccount;
import in.DAA.PhotoPlaza.entites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusinessAccountRepository extends JpaRepository<BusinessAccount, Long> {
    BusinessAccount findByUserId(Long userId);

    Optional<BusinessAccount> findByUser(User user);
}