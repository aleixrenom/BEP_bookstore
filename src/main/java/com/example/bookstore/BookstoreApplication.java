package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import com.example.bookstore.model.Category;
import com.example.bookstore.model.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			log.info("save three example books");
			crepository.save(new Category("Historical Literature"));
			crepository.save(new Category("Fantasy"));
			crepository.save(new Category("Other"));
			
			brepository.save(new Book("A Tale of Two Cities", "Charles Dickens", "isbn 1", 1859, 20, crepository.findByName("Historical Literature").get(0)));
			brepository.save(new Book("The Hobbit", "J. R. R. Tolkien", "isbn 2", 1937, 20, crepository.findByName("Fantasy").get(0)));
			brepository.save(new Book("Harry Potter and the Philosopher's Stone", "J. K. Rowling", "isbn 3", 1997, 20, crepository.findByName("Fantasy").get(0)));
			
			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			};
		};
	}
}
