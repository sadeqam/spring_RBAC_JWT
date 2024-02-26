package ir.sadeqam.spring_RBAC_JWT;

import ir.sadeqam.spring_RBAC_JWT.repository.entities.Authority;
import ir.sadeqam.spring_RBAC_JWT.repository.entities.Book;
import ir.sadeqam.spring_RBAC_JWT.repository.entities.Role;
import ir.sadeqam.spring_RBAC_JWT.repository.entities.User;
import ir.sadeqam.spring_RBAC_JWT.service.AuthorityService;
import ir.sadeqam.spring_RBAC_JWT.service.BookService;
import ir.sadeqam.spring_RBAC_JWT.service.RoleService;
import ir.sadeqam.spring_RBAC_JWT.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class SpringRbacJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRbacJwtApplication.class, args);
    }

    @Bean
        //TODO just for testing, remove it in production
    CommandLineRunner run(BookService bookService, UserService userService,
                          RoleService roleService, AuthorityService authorityService) {
        return args -> {
            if (bookService.isExists("DataBase 101")) return;

            var b1 = new Book("DataBase 101", "alireza testzade");
            var b2 = new Book("Hardware 101", "mohammad springabadi");

            bookService.insert(b1);
            bookService.insert(b2);

            var select = authorityService.insert(new Authority("select"));
            var create = authorityService.insert(new Authority("insert"));
            var change = authorityService.insert(new Authority("change"));
            var remove = authorityService.insert(new Authority("remove"));

            var adminAuthorities = Set.of(select, create, change, remove);
            var userAuthorities = Set.of(select, create);

            var admin = roleService.insert(new Role("ADMIN", adminAuthorities));
            var user = roleService.insert(new Role("USER", userAuthorities));

            userService.insert(new User("sadeq", "sadeq", Set.of(admin, user)));
            userService.insert(new User("mamad", "mamad", Set.of(user)));


        };
    }

}
