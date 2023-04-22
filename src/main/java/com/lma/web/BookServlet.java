package com.lma.web;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lma.model.Book;
//import com.lma.model.Book;
import com.lma.service.Book1;

//import org.springframework.context.support.ClassPathXmlApplicationContext;



@WebServlet("/")
public class BookServlet extends HttpServlet{
	
	Book1 b;
	public void init(){
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("book-cfg.xml");
	  b = (Book1)ctx.getBean("bkbean");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
     PrintWriter pw = response.getWriter();
     String ac = request.getServletPath();
     
     try{
    	 
    	 switch(ac){
    		 case"/new":
    			 showNewForm(request,response);
    			 break;
    		 case"/insert":
    			 insertBook(request,response);
    			 break;
    		 case"/delete":
    			 deleteBook(request,response);
    			 break;
    		 case"/edit":
    			 showEditForm(request,response);
    			 break;
    		 case"/update":
    			 updateBook(request,response);
    			 break;
    			 default: 
    				 listBook (request, response);
    				 break;
    			 
    			 
    	 }
     }catch(SQLException ex){
    	throw new ServletException(ex);}
     }
     
     private void listBook(HttpServletRequest request ,HttpServletResponse response) throws SQLException,IOException,ServletException {
    	 List listBook = b.getAllBooks();
       System.out.println(listBook);
       request.setAttribute("listBook", listBook);
       RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
     dispatcher.forward(request, response);
     }
     
     private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    		    throws ServletException, IOException {
    		        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
    		        dispatcher.forward(request, response);
    		    }

    		    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    		    throws SQLException, ServletException, IOException {
    		        int id = Integer.parseInt(request.getParameter("id"));
    		        Map existingBook = b.SelectBook(id);
    		        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
    		        request.setAttribute("Book", existingBook);
    		        dispatcher.forward(request, response);

    		    }

    		    private void insertBook(HttpServletRequest request, HttpServletResponse response)
    		    throws SQLException, IOException {
    		        String bookName = request.getParameter("BookName");
    		        String authorName = request.getParameter("AuthorName");
    		        long price = Long.parseLong("price");
    		        Book newBook = new Book(bookName, authorName, price);
    		        b.insertBook(newBook);
    		        response.sendRedirect("list");
    		    }

    		   

    		   

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
        b.deleteBook(id);
        response.sendRedirect("list");	
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
        String bookName = request.getParameter(" Book name");
        String authorName = request.getParameter("author Name");
        long price = Long.parseLong("price");
        Book book1 = new Book(id, bookName, authorName, price);
        b.updateBook(book1);
        response.sendRedirect("list");
	}

}
