package ir.sadeqam.spring_RBAC_JWT.repository.repositories;

import ir.sadeqam.spring_RBAC_JWT.repository.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Integer> {
    Boolean existsByName(String name);

}
