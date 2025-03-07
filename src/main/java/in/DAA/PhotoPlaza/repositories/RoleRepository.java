package in.DAA.PhotoPlaza.repositories;

import in.DAA.PhotoPlaza.entites.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(String name);
//    Role findByName(String name);
}
