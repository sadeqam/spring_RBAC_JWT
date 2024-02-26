package ir.sadeqam.spring_RBAC_JWT.repository.repositories;

import ir.sadeqam.spring_RBAC_JWT.repository.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);

}
