package tech.maret.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tech.maret.bookstore.model.Book;
import tech.maret.bookstore.model.BookRepository;
import tech.maret.bookstore.model.Category;
import tech.maret.bookstore.model.CategoryRepository;
import tech.maret.bookstore.model.User;
import tech.maret.bookstore.model.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("save some books ...");
			crepository.save(new Category("Dev"));
			crepository.save(new Category("Polar"));
			crepository.save(new Category("Life"));
			crepository.save(new Category("Romantic"));
			brepository.save(new Book("Java in a nutshell", "Johnson", "2016", "1234rfef341", 12.50, crepository.findByName("Dev")));
			brepository.save(new Book("Homosapiens", "Harry Luse", "2013", "nehg43uhuhf4", 19.90, crepository.findByName("Life")));
			brepository.save(new Book("Hello world!", "Patrick", "2015", "fejrhh38474", 25.50, crepository.findByName("Dev")));
			brepository.save(new Book("Murdered", "Micode", "2010", "hrurhfg9394j", 20, crepository.findByName("Polar")));	
			
			User user1 = new User("user", "$2a$10$htMeYR95oApoqIO6yKPxVuooN.EhKAveLg6hf207TCFt7N9RuVTA2", "user@maret.tech$","USER");
			User user2 = new User("admin", "$2a$10$O7XFyiK.HXt4e9VlLxmoR.Ovfz7wTiW.XE5PQQuSeg/d3.HfniTh6", "admin@maret.tech", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
		};
	}
}
