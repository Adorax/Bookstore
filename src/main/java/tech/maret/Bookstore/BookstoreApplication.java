package tech.maret.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tech.maret.bookstore.model.Book;
import tech.maret.bookstore.model.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			log.info("save some books ...");
			repository.save(new Book("Java in a nutshell", "Johnson", "2016", "1234rfef341", 12.50));
			repository.save(new Book("Homosapiens", "Harry Luse", "2013", "nehg43uhuhf4", 19.90));
			repository.save(new Book("Hello world!", "Patrick", "2015", "fejrhh38474", 25.50));
			repository.save(new Book("Murdered", "Micode", "2010", "hrurhfg9394j", 20));	
		};
	}
}
