package tech.maret.bookstore.web;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import tech.maret.bookstore.model.Book;
import tech.maret.bookstore.model.BookRepository;
import tech.maret.bookstore.model.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository brepository;
	@Autowired
	private CategoryRepository cbrepository;
	
	@GetMapping("/login")
	public String login() { return "login";}
	
	@GetMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", brepository.findAll());
		return "bookList";
	}
	
	@GetMapping("/books")
	public @ResponseBody List<Book> bookListRest() {
		return (List<Book>)brepository.findAll();
	}

	@GetMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("category", cbrepository.findAll());
		return "addbook";
	}
	
	@PostMapping("/save")
	public String save(Book book){
		brepository.save(book);
		return "redirect:booklist";
	}	
	
	@GetMapping(value = "/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		brepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	@GetMapping(value = "/book/{id}")
	public String updateBook(@PathVariable("id") Long bookId, Model model) {
		Book book = brepository.findById(bookId).orElse(null);
		if(book == null) { 
			System.out.println("book with id: "+bookId+" not find");
			return "redirect:booklist";
		}
		model.addAttribute("book", book);
		model.addAttribute("category", cbrepository.findAll());
		return "editbook";
	}
	
	@GetMapping(value = "/getbook/{id}")
	public @ResponseBody Book findBookRest(@PathVariable("id") Long bookId) {
		return (Book) brepository.findById(bookId).orElse(null);
	}
	
	@RequestMapping(value="/book/{id}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ADMIN')")
	public String deleteABook(@PathVariable("id") Long bookId) {
		brepository.deleteById(bookId);
		return "The book with Id : "+bookId + "is deleted.";
	}
	
	@RequestMapping(value="/book", method = RequestMethod.POST)
	public ResponseEntity<Object> createABook(@RequestBody Book book) {
		Book newBook = brepository.save(book);
		
		//Go to the book that was just created
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newBook.getIdB()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	
	@PostMapping("/update/{id}")
	public String updateBook(@PathVariable("id") Long bookId, Book book){
		brepository.deleteById(bookId);
		book.setIdB(bookId);
		brepository.save(book);
		return "redirect:../booklist";
	}
	
	
}
