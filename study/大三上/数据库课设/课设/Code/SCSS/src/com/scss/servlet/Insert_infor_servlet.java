package com.scss.servlet;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scss.database.manage.DBS_insert;
import com.scss.database.tables.Student_training;

/**
 * Servlet implementation class Insert_infor_servlet
 */
@WebServlet("/insert_infor_servlet")
public class Insert_infor_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert_infor_servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//…Ë÷√±‡¬Î
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//…Ë÷√±‡¬Î
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String info_type = request.getParameter("info_type");
		if (info_type.equals("student_training")) insert_student_traing(request,response);
	}
	
	private void insert_student_traing(HttpServletRequest request, HttpServletResponse response) {
		Student_training stu_tra = new Student_training();
		stu_tra.setStudent_id(request.getParameter("student_id"));
		stu_tra.setDate(Date.valueOf(request.getParameter("date")));
		stu_tra.setLevel(request.getParameter("level"));
		stu_tra.setType(request.getParameter("type"));
		stu_tra.setDescription(request.getParameter("description"));
		System.out.println("xxxxxx:"+stu_tra.getDescription());
		DBS_insert.insert(stu_tra);
	}

}
