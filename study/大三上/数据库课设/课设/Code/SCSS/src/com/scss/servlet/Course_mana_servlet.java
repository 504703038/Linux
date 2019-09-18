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
import com.scss.database.tables.Cour_info;
import com.scss.database.tables.Course;
import com.scss.database.tables.Stu_info;

/**
 * Servlet implementation class Course_mana_servlet
 */
@WebServlet("/course_mana_servlet")
public class Course_mana_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Course_mana_servlet() {
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
		String course_id = request.getParameter("course_id");
		if (course_id!=null) course_id=(course_id.equals("")?null:course_id);
		String course_name = request.getParameter("course_name");
		if (course_name!=null) course_name=(course_name.equals("")?null:course_name);
		Cour_info cour = new Cour_info();
		cour.setCourse_id(course_id); cour.setCourse_name(course_name);
		ArrayList<Cour_info> list =DBS_select.select(cour);
		String json = gson.toJson(list);
		request.setAttribute("data", json);
		RequestDispatcher rd = request.getRequestDispatcher("course_mana.jsp");
		rd.forward(request, response);
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		System.out.println("delete:"+data);
		//ÐÞ¸Ä²Ù×÷
		select(request, response);
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson =new Gson();
		String data = request.getParameter("data");
		Course course = gson.fromJson(data, Course.class);
		System.out.println("update:"+data);
		//É¾³ý²Ù×÷		begin
		DBS_delete.delete(course);
		//end
		select(request, response);
	}

}
