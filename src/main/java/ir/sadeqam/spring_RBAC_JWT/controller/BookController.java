package ir.sadeqam.spring_RBAC_JWT.controller;

import ir.sadeqam.spring_RBAC_JWT.repository.entities.Book;
import ir.sadeqam.spring_RBAC_JWT.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(
        path = "api/books",
        produces = MediaType.APPLICATION_JSON_VALUE
)
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping
    @PreAuthorize("hasAuthority('select')")
    public List<Book> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('select')")
    public Book find(@PathVariable Integer id) {
        return service.find(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('insert')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insert(@Valid @RequestBody Book book) {
        service.insert(book);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('remove')")
    public void remove(@PathVariable Integer id) {
        service.remove(id);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('change')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Book book) {
        service.update(book);
    }

}
