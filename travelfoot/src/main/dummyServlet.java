package main;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class dummyServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		sc.getRequestDispatcher("/login.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String pw = req.getParameter("pw");
		
		String[] row = new DAO().getRow(email);
		
		if(row == null) {
			ServletContext sc = this.getServletContext();
			sc.getRequestDispatcher("/login.html").forward(req, resp);
		} 
		else if(pw.equals(row[1])) {
			resp.sendRedirect("/confirm.html");
		}
		else {
			ServletContext sc = this.getServletContext();
			sc.getRequestDispatcher("/login.html").forward(req, resp);
		}
	}
}
