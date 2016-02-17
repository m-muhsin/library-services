package com.nsbm;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.nsbm.model.Book;
import com.nsbm.repository.BookRepository;
import com.nsbm.repository.BookRepositoryImpl;

@Path("books")
public class BookResource {

	private BookRepository bookRepository = new BookRepositoryImpl();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String get() {
		//test
		return "Book Service!!!";
	}

	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_XML)
	public List<Book> getBooks() {
		return bookRepository.getBooks();
	}
	
	@GET
	@Path("{bookId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getBook(@PathParam("bookId") String bookId) {
		// return book by id

		// throwing different error messages is similar to throwing different
		// exceptions
		if (bookId == null || bookId.equals("")) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		Book book = bookRepository.getBook(bookId);

		if (book == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		// ok() = response status 200
		return Response.ok().entity(book).build();
	}

	@POST
	@Path("book")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String createBook(Book book) {
		String b = bookRepository.createBook(book);
		return b;
		
	}

}
