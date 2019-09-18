package com.scss.servlet;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.Connection;
import com.scss.database.manage.DBS_select;
import com.scss.database.tables.User;

/**
 * Servlet implementation class login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		//获取登录账号和密码
		String user_id = request.getParameter("user_id");
		String password = request.getParameter("password");
		//验证登录信息并将用户信息返回
		User user = login_check(user_id,password);
		//验证账号和密码
		if (user!=null){
			RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
			request.removeAttribute("user");
			request.setAttribute("user", user);
			System.out.print("login_check"+user);
			rd.include(request, response);
		}
		else {
			
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			request.setAttribute("login_failed", false);
			rd.include(request, response);
		}
	}

	private User login_check(String id, String password) {
		User user = new User();
		user.setUser_id(id); user=DBS_select.select(user);
		if (user!=null && !user.getPassword().equals(password)){
			user=null;
		}
		return user;
	}

}
