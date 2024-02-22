package ir.sadeqam.spring_RBAC_JWT.repository.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "book_id")
    private Integer id;

    @NotBlank(message = "book name can't be blank")
    @Column(name = "book_name")
    private String name;

    @NotBlank(message = "book writer can't be blank")
    @Column(name = "book_writer")
    private String writer;

    public Book() {
    }

    public Book(String name, String writer) {
        this.name = name;
        this.writer = writer;
    }

    public Book(Integer id, String name, String writer) {
        this.id = id;
        this.name = name;
        this.writer = writer;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        return Objects.equals(id, ((Book) other).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Book{ id= %d, name='%s', writer='%s' }".formatted(id, name, writer);
    }
}
