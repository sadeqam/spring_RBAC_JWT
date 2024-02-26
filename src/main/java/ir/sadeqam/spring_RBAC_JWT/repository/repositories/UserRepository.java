package ir.sadeqam.spring_RBAC_JWT.repository.repositories;

import ir.sadeqam.spring_RBAC_JWT.repository.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

}
