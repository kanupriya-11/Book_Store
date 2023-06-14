package com.bookStore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookStore.entity.BookList;
import com.bookStore.repository.MyBookRepository;

@Service
public class MyBookListService {

	@Autowired
	private MyBookRepository myBookRepository;
	
	public void save(BookList book)
	{
		myBookRepository.save(book);
	}
	
	public List<BookList> getMyAllBooks()
	{
		return myBookRepository.findAll();
	}
	
	public void deleteById(int id)
	{
		myBookRepository.deleteById(id);;
	}
}
