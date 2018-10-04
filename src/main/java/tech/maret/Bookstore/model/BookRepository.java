package tech.maret.bookstore.model;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
	//Declare the query methods there
	List<Book> findByTitle(String title);
}
