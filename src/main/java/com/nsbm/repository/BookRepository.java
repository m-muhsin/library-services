package com.nsbm.repository;

import java.util.List;

import com.nsbm.model.Book;

public interface BookRepository {

	public List<Book> getBooks();

	Book getBook(String bookId);

	public String createBook(Book book);
	
}
