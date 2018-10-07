package tech.maret.bookstore.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long idC;
	String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	List<Book> books;
	
	
	public Category() {}
	
	public Category(String name) {
		this.name = name;
	}
	
	public long getIdC() {
		return idC;
	}
	public void setIdC(long idC) {
		this.idC = idC;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Category: " + name;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	
}
