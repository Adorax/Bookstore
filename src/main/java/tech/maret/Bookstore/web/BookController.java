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
import tech.maret.bookstore.model.CategoryRepository;

@Controller
public class BookController {

	@Autowired
	private BookRepository brepository;
	@Autowired
	private CategoryRepository cbrepository;
	
	@GetMapping("/booklist")
	public String bookList(Model model) {
		model.addAttribute("books", brepository.findAll());
		return "bookList";
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
	public String deleteBook(@PathVariable("id") Long bookId, Model model) {
		brepository.deleteById(bookId);
		return "redirect:../booklist";
	}
	
	@GetMapping(value = "/{id}")
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
	
	@PostMapping("/update/{id}")
	public String updateBook(@PathVariable("id") Long bookId, Book book){
		brepository.deleteById(bookId);
		book.setIdB(bookId);
		brepository.save(book);
		return "redirect:../booklist";
	}
}
