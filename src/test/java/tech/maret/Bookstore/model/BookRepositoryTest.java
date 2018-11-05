package tech.maret.bookstore.model;



import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository bookR;
	
	private Book book;
	
	@Test
	public void createNewBook() {
		book = new Book("JavaTesting", "Java Jovo", "2012", "nfeniei3310", 33.50, new Category("Testing"));
		bookR.save(book);
		
		assertThat(book.getIdB()).isNotNull();
	}
	
	@Test
	public void deleteBook() {
		List<Book> books = bookR.findByTitle("Homosapiens");
		Long id = books.get(0).getIdB();
		
		bookR.deleteById(id);	
		assertThat(bookR.findById(id)).isEmpty();
	}
	
	@Test
	public void findBookByTitle() {
		List<Book> books = bookR.findByTitle("Murdered");   
		assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Micode");
	}
}
