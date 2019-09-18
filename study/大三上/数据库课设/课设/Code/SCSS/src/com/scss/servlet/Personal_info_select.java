package com.scss.servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Stack;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.scss.database.manage.DBS_select;
import com.scss.database.manage.DBS_update;
import com.scss.database.tables.Ins_info;
import com.scss.database.tables.Instructor;
import com.scss.database.tables.Stu_info;
import com.scss.database.tables.Student;

/**
 * Servlet implementation class Personal_info_select
 */
@WebServlet("/personal_info_select")
public class Personal_info_select extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Personal_info_select() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String role = request.getParameter("role");
		if (role!=null) role=(role.equals("")?null:role);
		String user_id = request.getParameter("user_id");
		if (user_id!=null) user_id=(user_id.equals("")?null:user_id);
		
		System.out.println("person_info_servlet: user:"+user_id+" role"+role);
		
		Stu_info stu = new Stu_info();
		Ins_info ins = new Ins_info();
		if (role.equals("0")) {
			stu.setStudent_id(user_id);
			ArrayList<Stu_info> list =DBS_select.select(stu);
			System.out.println(list.size());
			if (list.size()==1) stu=list.get(0);
			request.setAttribute("stu_info", stu);
		}
		if (role.equals("1")) {
			ins.setInstructor_id(user_id);
			ArrayList<Ins_info> list =DBS_select.select(ins);
			if (list.size()==1) ins=list.get(0);
			request.setAttribute("ins_info", ins);
		}
		RequestDispatcher rd = request.getRequestDispatcher("person_info.jsp");
		request.setAttribute("role", role);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_id = request.getParameter("user_id");
		if (user_id!=null) user_id=(user_id.equals("")?null:user_id);
		String role = request.getParameter("role");
		if (role!=null) role=(role.equals("")?null:role);
		Date birthday = Date.valueOf(request.getParameter("birthday"));
		String phone = request.getParameter("phone");
		
		Student stu = new Student();
		Instructor ins = new Instructor();
		if (role.equals("0")) {
			stu.setStudent_id(user_id); stu = DBS_select.select(stu).get(0);
			stu.setPhone(phone);
			stu.setBirthday(birthday);
			DBS_update.update(stu);
		}
		if (role.equals("1")) {
			ins.setInstructor_id(user_id); ins = DBS_select.select(ins).get(0);
			System.out.println(ins);
			ins.setPhone(phone);
			ins.setBirthday(birthday);
			DBS_update.update(ins);
		}
		request.setAttribute("changed", "true");
		doGet(request,response);
	}

}
