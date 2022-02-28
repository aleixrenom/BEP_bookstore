package com.example.bookstore.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bookstore.model.Book;
import com.example.bookstore.model.BookRepository;
import com.example.bookstore.model.CategoryRepository;
import com.example.bookstore.model.Friend;

@Controller
public class BookController {
	@Autowired
	private BookRepository brepository;
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/hello")
	public String hello(
			@RequestParam(name = "name") String name, 
			@RequestParam(name = "age") String age, 
			Model model
			) {
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "hello";
	}
	
	@RequestMapping("/listofbooks")
	public String listofbooks(Model model) {
		Book book1 = new Book("A Tale of Two Cities", "Charles Dickens");
		Book book2 = new Book("The Hobbit", "J. R. R. Tolkien");
		Book book3 = new Book("The Little Prince", "Antoine de Saint-Exupéry");
		List<Book> books = new ArrayList<Book>();
		books.add(book1);
		books.add(book2);
		books.add(book3);
		
		model.addAttribute("listofbooks", books);
		return "listofbooks";
	}
	
	@RequestMapping(value="/friendlist", method=RequestMethod.GET)
	public String friendlistSubmit(@ModelAttribute Friend friend, Model model) {
		Friend friend1 = new Friend("Mark Z. Spot");
		Friend friend2 = new Friend("Dill Doe");
		List<Friend> friendlist = new ArrayList<Friend>();
		friendlist.add(friend1);
		friendlist.add(friend2);
		friendlist.add(friend);
		
		model.addAttribute("friendlist", friendlist);
		model.addAttribute("friend", friend);
		return "friendlist";
	}

    @RequestMapping(value= {"/booklist"})
    public String bookList(Model model) {	
        model.addAttribute("books", brepository.findAll());
        return "booklist";
    }
    
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {	
        return (List<Book>) brepository.findAll();
    }    

    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {	
    	return brepository.findById(bookId);
    }
    
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	brepository.deleteById(bookId);
        return "redirect:../booklist";
    }
    
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }
    
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model) {
    	model.addAttribute("book", brepository.findById(bookId));
    	model.addAttribute("categories", crepository.findAll());
    	return "editbook";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        brepository.save(book);
        return "redirect:booklist";
    }
}