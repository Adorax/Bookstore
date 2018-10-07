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

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository) {
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
		};
	}
}
