package com.scss.servlet;

import java.io.*;
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
import com.scss.database.tables.Student_training;

/**
 * Servlet implementation class student_training_info
 */
@WebServlet("/student_training_info")
public class Student_training_info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Student_training_info() {
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
		
		select(request, response);
	}
	
	private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson =new Gson();
		String student_id = request.getParameter("student_id");
		if (student_id!=null) student_id=(student_id.equals("")?null:student_id);
		String student_name = request.getParameter("student_name");
		if (student_name!=null) student_name=(student_name.equals("")?null:student_name);
		
		Student_training stu_tra = new Student_training();
		stu_tra.setStudent_id(student_id); stu_tra.setStudent_name(student_name);
		ArrayList<Student_training> list =DBS_select.select(stu_tra);
		String json = gson.toJson(list);
		request.setAttribute("data", json);
		RequestDispatcher rd = request.getRequestDispatcher("award.jsp");
		rd.forward(request, response);
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String data = request.getParameter("data");
		Student_training stu_tra = gson.fromJson(data, Student_training.class);
		System.out.println("student_training_servlet_update:"+data);
		//ÐÞ¸Ä²Ù×÷		begin
		
		
		//end
		select(request, response);
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String data = request.getParameter("data");
		Student_training stu_tra = gson.fromJson(data, Student_training.class);
		System.out.println("student_training_servlet_delete:"+data);
		//É¾³ý²Ù×÷		begin
		DBS_delete.delete(stu_tra);
		//end
		select(request, response);
	}
}
