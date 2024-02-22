package ir.sadeqam.spring_RBAC_JWT.service;

import ir.sadeqam.spring_RBAC_JWT.repository.entities.Book;
import ir.sadeqam.spring_RBAC_JWT.repository.repositories.BookRepository;
import ir.sadeqam.spring_RBAC_JWT.service.exceptions.DataNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Transactional
    public Book insert(Book book) {
        return repository.save(book);
    }

    public Book find(Integer id) {
        var result = repository.findById(id);
        if (result.isEmpty())
            throw new DataNotFoundException("book with id %d not found".formatted(id));

        return result.get();
    }

    public Boolean isExists(String name) {
        return repository.existsByName(name);
    }

    @Transactional
    public void remove(Integer id) {
        if (!repository.existsById(id))
            throw new DataNotFoundException("book with id %d not found".formatted(id));

        repository.deleteById(id);
    }

    @Transactional
    public void update(Book book) {
        if (!repository.existsById(book.getId()))
            throw new DataNotFoundException("book with id %d not found".formatted(book.getId()));

        var forUpdate = repository.getReferenceById(book.getId());
        forUpdate.setName(book.getName());
        forUpdate.setWriter(book.getWriter());
    }

    public List<Book> findAll() {
        //TODO don't forge pagination
        return repository.findAll();
    }

}
