package ir.sadeqam.spring_RBAC_JWT.repository.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "role_name", unique = true)
    @NotBlank(message = "role name can't be blank")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_authority",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id")
    )
    private Set<Authority> authorities;


    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public Role(String name, Set<Authority> authorities) {
        this.name = name;
        this.authorities = authorities;
    }

    public Role(Integer id, String name, Set<Authority> authorities) {
        this.id = id;
        this.name = name;
        this.authorities = authorities;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;

        return Objects.equals(name, ((Role) other).getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
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

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public String toString() {
        return "Role{ id= %d, name= '%s', authorities= %s}".formatted(id, name, authorities.toString());
    }
}
