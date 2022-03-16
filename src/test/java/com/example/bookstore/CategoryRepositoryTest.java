package com.example.bookstore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.bookstore.model.Category;
import com.example.bookstore.model.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository repository;
	
	@Test
	public void createNewCategory() {
		Category category = new Category("Category");
		repository.save(category);
		assertThat(category.getCategoryid()).isNotNull();
	}
	
	@Test
	public void searchForCategory() {
		List<Category> categories = repository.findByName("Fantasy");
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getName()).isEqualTo("Fantasy");
	}
	
	@Test
	public void deleteCategory() {
		Category category = new Category("Category");
		repository.save(category);
		repository.deleteById(category.getCategoryid());
		List<Category> categories = repository.findByName("Category");
		assertThat(categories).hasSize(0);
	}
}
