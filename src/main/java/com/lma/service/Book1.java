package com.lma.service;

import java.util.List;
import java.util.Map;


import com.lma.model.Book;

public interface Book1 {
	public void insertBook(Book book);
    public Map SelectBook(int id);
    public List getAllBooks();
    public void updateBook(Book book);
    public void deleteBook(int id);

}
