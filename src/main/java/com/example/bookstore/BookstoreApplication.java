package com.example.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			log.info("save three example books");
			repository.save(new Book("A Tale of Two Cities", "Charles Dickens", 1859, "isbn 1", 20));
			repository.save(new Book("The Hobbit", "J. R. R. Tolkien", 1937, "isbn 2", 20));
			repository.save(new Book("Harry Potter and the Philosopher's Stone", "J. K. Rowling", 1997, "isbn 3", 20));
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			};
		};
	}
}
