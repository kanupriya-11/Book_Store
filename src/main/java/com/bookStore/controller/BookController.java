package com.bookStore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bookStore.entity.Book;
import com.bookStore.entity.BookList;
import com.bookStore.service.BookService;
import com.bookStore.service.MyBookListService;

@Controller
public class BookController {

	@Autowired
	private BookService bookService;
	
	@Autowired
	private MyBookListService myBookListService;
	
	@GetMapping("/")
	public String home()
	{
		return "home";
	}
	
	@GetMapping("/book_register")
	public String bookRegister()
	{
		return "bookRegister";
	}
	
	@GetMapping("/available_books")
	public ModelAndView getAllBooks()
	{
		List<Book> list = bookService.getAllBooks();
//		ModelAndView m = new ModelAndView();
//		m.setViewName("bookList");
//		m.addObject("book", list);
		return new ModelAndView("bookList","book",list);
	}
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book book)
	{
		bookService.save(book);
		return "redirect:/available_books";
	}
	
	@GetMapping("/my_books")
	public String myBooks(Model model)
	{
		List<BookList> list = myBookListService.getMyAllBooks();
		model.addAttribute("book",list);
		return "myBooks";
	}
	
	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id)
	{
		Book book = bookService.getBookById(id);
		BookList myBook = new BookList(book.getId(), book.getName(), book.getAuthor(), book.getPrice());
		myBookListService.save(myBook);
		return "redirect:/my_books";
	}
	
//	@RequestMapping("/deleteMyList/{id}")
//	public String deleteMyBook(@PathVariable("id") int id)
//	{
//		myBookListService.deleteById(id);
//		return "redirect:/my_books";
//	}
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model)
	{
		Book book = bookService.getBookById(id);
		model.addAttribute("book", book);
		return "bookEdit";
	}
	
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id)
	{
		bookService.deleteBook(id);
		return "redirect:/available_books";
	}
}
