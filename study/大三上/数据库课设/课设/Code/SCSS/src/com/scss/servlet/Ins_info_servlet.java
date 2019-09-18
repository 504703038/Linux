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
import com.scss.database.tables.Ins_info;
import com.scss.database.tables.Instructor;
import com.scss.database.tables.Student;

/**
 * Servlet implementation class Ins_info_servlet
 */
@WebServlet("/ins_info_servlet")
public class Ins_info_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Ins_info_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String op = request.getParameter("op");
		if (op.equals("select")) select(request, response);
		else if (op.equals("update")) update(request, response);
		else if (op.equals("delete")) delete(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson =new Gson();
		String instructor_id = request.getParameter("instructor_id");
		if (instructor_id!=null) instructor_id=(instructor_id.equals("")?null:instructor_id);
		String instructor_name = request.getParameter("instructor_name");
		if (instructor_name!=null) instructor_name=(instructor_name.equals("")?null:instructor_name);
		Ins_info ins = new Ins_info();
		ins.setInstructor_id(instructor_id); ins.setInstructor_name(instructor_name);
		ArrayList<Ins_info> list =DBS_select.select(ins);
		String json = gson.toJson(list);
		request.setAttribute("data", json);
		RequestDispatcher rd = request.getRequestDispatcher("ins_info.jsp");
		rd.forward(request, response);
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		System.out.println("update:"+data);
		//ÐÞ¸Ä²Ù×÷
		select(request, response);
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String data = request.getParameter("data");
		Instructor ins = gson.fromJson(data, Instructor.class);
		System.out.println("delete:"+data);
		//É¾³ý²Ù×÷		begin
		DBS_delete.delete(ins);
		//end
		select(request, response);
	}

}
