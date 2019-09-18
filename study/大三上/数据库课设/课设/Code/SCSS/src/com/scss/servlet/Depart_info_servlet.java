package com.scss.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.scss.database.manage.DBS_delete;
import com.scss.database.manage.DBS_select;
import com.scss.database.tables.Department;
import com.scss.database.tables.Major;
import com.scss.database.tables.Student_training;

/**
 * Servlet implementation class Depart_info_servlet
 */
@WebServlet("/depart_info_servlet")
public class Depart_info_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Depart_info_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ÉèÖÃ±àÂë
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		String op = request.getParameter("op");
		if (op.equals("select")) select(request, response);
		else if (op.equals("update")) update(request, response);
		else if (op.equals("delete")) delete(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//ÉèÖÃ±àÂë
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		doGet(request, response);
	}
	
	private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson =new Gson();
		String depart_id = request.getParameter("depart_id");
		if (depart_id!=null) depart_id=(depart_id.equals("")?null:depart_id);
		String depart_name = request.getParameter("depart_name");
		if (depart_name!=null) depart_name=(depart_name.equals("")?null:depart_name);
		Department depart = new Department();
		depart.setDepart_id(depart_id); depart.setDepart_name(depart_name);
		ArrayList<Department> list =DBS_select.select(depart);
		String json = gson.toJson(list);
		request.setAttribute("data", json);
		RequestDispatcher rd = request.getRequestDispatcher("depart_info.jsp");
		rd.forward(request, response);
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		System.out.println("depart_info_servlet_update:"+data);
		//ÐÞ¸Ä²Ù×÷
		select(request, response);
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String data = request.getParameter("data");
		Department depart = gson.fromJson(data, Department.class);
		System.out.println("depart_info_servlet_delete:"+data);
		//É¾³ý²Ù×÷		begin
		DBS_delete.delete(depart);
		//end
		select(request, response);
	}

}
