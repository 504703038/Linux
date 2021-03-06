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
import com.scss.database.tables.Stu_info;
import com.scss.database.tables.Student;
import com.scss.database.tables.Student_training;

/**
 * Servlet implementation class Stu_info_servlet
 */
@WebServlet("/stu_info_servlet")
public class Stu_info_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stu_info_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ñ���
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
		//���ñ���
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

//		doGet(request, response);
	}
	
	private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson =new Gson();
		String student_id = request.getParameter("student_id");
		if (student_id!=null) student_id=(student_id.equals("")?null:student_id);
		String student_name = request.getParameter("student_name");
		if (student_name!=null) student_name=(student_name.equals("")?null:student_name);
		Stu_info stu = new Stu_info();
		stu.setStudent_id(student_id); stu.setStudent_name(student_name);
		ArrayList<Stu_info> list =DBS_select.select(stu);
		String json = gson.toJson(list);
		request.setAttribute("data", json);
		RequestDispatcher rd = request.getRequestDispatcher("stu_info.jsp");
		rd.forward(request, response);
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		System.out.println("student_info_servlet_update:"+data);
		//�޸Ĳ���
		select(request, response);
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String data = request.getParameter("data");
		Student stu = gson.fromJson(data, Student.class);
		System.out.println("student_info_servlet_delete:"+data);
		//ɾ������		begin
		DBS_delete.delete(stu);
		//end
		select(request, response);
	}
}
