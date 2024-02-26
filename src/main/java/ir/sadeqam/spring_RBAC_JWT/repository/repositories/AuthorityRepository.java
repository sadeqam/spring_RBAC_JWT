package ir.sadeqam.spring_RBAC_JWT.repository.repositories;

import ir.sadeqam.spring_RBAC_JWT.repository.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

    Optional<Authority> findByAuthority(String authority);

}
