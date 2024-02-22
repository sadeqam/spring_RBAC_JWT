package ir.sadeqam.spring_RBAC_JWT;

import ir.sadeqam.spring_RBAC_JWT.repository.entities.Book;
import ir.sadeqam.spring_RBAC_JWT.service.BookService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringRbacJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRbacJwtApplication.class, args);
	}

	@Bean //TODO just for testing, remove it in production
	CommandLineRunner run(BookService bookService){
		return args -> {
			if (bookService.isExists("DataBase 101")) return;

			var b1 = new Book("DataBase 101", "alireza testzade");
			var b2 = new Book("Hardware 101", "mohammad springabadi");

			bookService.insert(b1);
			bookService.insert(b2);
		};
	}

}
