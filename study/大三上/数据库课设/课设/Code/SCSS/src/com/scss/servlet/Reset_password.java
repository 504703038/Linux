package com.scss.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scss.database.manage.DBS_select;
import com.scss.database.manage.DBS_update;
import com.scss.database.tables.User;

/**
 * Servlet implementation class Reset_password
 */
@WebServlet("/reset_password")
public class Reset_password extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Reset_password() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user_id = (String)request.getParameter("user_id");
		if (user_id!=null) user_id=(user_id.equals("")?null:user_id);
		String pass = (String)request.getParameter("new_password");
		if (pass!=null) pass=(pass.equals("")?null:pass);
		User user = new User();
		user.setUser_id(user_id); user=DBS_select.select(user);
		
		user.setPassword(pass);
		DBS_update.update(user);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}

}
