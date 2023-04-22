package com.lma.bean;

import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.lma.model.Book;
import com.lma.service.Book1;
;

public class BookBean implements Book1 {
	private HibernateTemplate ht;
    @Transactional
	@Override
	public void insertBook(Book book) {
		this.ht.saveOrUpdate(book);
		
	}

	@Override
	public Map SelectBook(int id) {
		Map m= (Map) this.ht.get(Book.class, id);	
		return m;
	}

	@Override
	public List getAllBooks() {
		List l=this.ht.loadAll(Book.class);
		return l;
	}
	 @Transactional
	@Override
	public void updateBook(Book book) {
		this.ht.update(book);
		
	}
	 @Transactional
	@Override
	public void deleteBook(int id) {
		Book bk=this.ht.get(Book.class, id);	
	   this.ht.delete(bk);
	}


}
