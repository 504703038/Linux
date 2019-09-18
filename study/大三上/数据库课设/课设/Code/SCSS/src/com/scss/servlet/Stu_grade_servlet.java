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
import com.scss.database.manage.DBS_select;
import com.scss.database.tables.Section_info;
import com.scss.database.tables.Select_info;
import com.scss.database.tables.Student_grade;
import com.scss.database.tables.Student_training;

/**
 * Servlet implementation class Stu_grade_servlet
 */
@WebServlet("/stu_grade_servlet")
public class Stu_grade_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stu_grade_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String op = request.getParameter("op");
		if (op.equals("select")) select(request, response);
//		else if (op.equals("update")) update(request, response);
//		else if (op.equals("delete")) delete(request, response);
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
		String student_id = request.getParameter("student_id");
		if (student_id!=null) student_id=(student_id.equals("")?null:student_id);
		String limi = request.getParameter("limi");
		if (limi!=null) limi=(limi.equals("")?null:limi);
		Student_grade stu_grade = new Student_grade();
		stu_grade.setStudent_id(student_id); stu_grade.setLimi(limi);
		ArrayList<Student_grade> select_list =DBS_select.select(stu_grade);
		String json = gson.toJson(select_list);
		request.setAttribute("data", json);
		RequestDispatcher rd = request.getRequestDispatcher("stu_grade.jsp");
		rd.forward(request, response);
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		System.out.println("update:"+data);
		//É¾³ý²Ù×÷
		select(request, response);
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		System.out.println("delete:"+data);
		//ÐÞ¸Ä²Ù×÷
		select(request, response);
	}

}
