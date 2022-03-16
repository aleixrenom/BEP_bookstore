package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import com.example.bookstore.model.Category;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repository;
	
	@Test
	public void createNewBook() {
		Book book = new Book("Title", "Author", "0123456789", 2022, 20, new Category("Category"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test
	public void searchForBook() {
		List<Book> books = repository.findByTitle("A Tale of Two Cities");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Charles Dickens");
	}
	
	@Test
	public void deleteBook() {
		Book book = new Book("Title", "Author", "0123456789", 2022, 20, new Category("Category"));
		repository.save(book);
		repository.deleteById(book.getId());
		List<Book> books = repository.findByTitle("Title");
		assertThat(books).hasSize(0);
	}
}
