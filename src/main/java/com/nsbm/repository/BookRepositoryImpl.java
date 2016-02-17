package com.nsbm.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nsbm.model.Book;
import com.nsbm.model.DbConn;

public class BookRepositoryImpl implements BookRepository {
	
	private DbConn conn;

	@Override
	public List<Book> getBooks() {
		conn = new DbConn();
		String sql = "SELECT * FROM tblbooks";
		conn.connectDB();
		List<Book> books = new ArrayList<Book>();
		try {
			Statement stmt = conn.conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Book book = new Book();
				book.setId(rs.getString("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				books.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.closeDB();
		return books;
	}
	
	@Override
	public Book getBook(String bookId) {
		
		conn = new DbConn();
		String sql = "SELECT * FROM tblbooks where id = ?";
		conn.connectDB();
		Book book = null;

		try {
			PreparedStatement pstmt = conn.conn.prepareStatement(sql);
			pstmt.setString(1, bookId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				book = new Book();
				book.setId(rs.getString("id"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		conn.closeDB();
		return book;
	}


	@Override
	public String createBook(Book book) {
		conn = new DbConn();
		String sql = "INSERT INTO dblibrary.tblbooks " + "(title, author) "
				+ "VALUES (?, ?)";
		conn.connectDB();
		int confirm = 0;

		try {
			PreparedStatement pstmt = conn.conn.prepareStatement(sql);
			pstmt.setString(1, book.getTitle());
			pstmt.setString(2, book.getAuthor());
			confirm = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (confirm == 1) {
			return "success";
		}
		return "fail";
	}

}
