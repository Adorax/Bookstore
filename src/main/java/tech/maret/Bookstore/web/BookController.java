package tech.maret.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import tech.maret.bookstore.model.Book;
import tech.maret.bookstore.model.BookRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository repository;
	
	@GetMapping("/index")
	public String index(Model m) {
		List<Book> books = repository.findByTitle("ok");
		m.addAttribute("books", books);
		return "index";
	}
	
	@GetMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", repository.findAll());
		return "bookList";
	}
	
	@GetMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		return "addbook";
	}
	
	@PostMapping("/save")
	public String save(Book book){
		repository.save(book);
		return "redirect:booklist";
	}	
	
	@GetMapping(value = "/delete/{id}")
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		repository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	@GetMapping(value = "/{id}")
	public String updateBook(@PathVariable("id") Long bookId, Model model) {
		Book book = repository.findById(bookId).orElse(null);
		if(book == null) { 
			System.out.println("book with id: "+bookId+" not find");
			return "redirect:booklist";
		}
		model.addAttribute("book", book);
		return "editbook";
	}
	
	@PostMapping("/update/{id}")
	public String updateBook(@PathVariable("id") Long bookId, Book book){
		repository.deleteById(bookId);
		book.setId(bookId);
		repository.save(book);
		return "redirect:../booklist";
	}
}
