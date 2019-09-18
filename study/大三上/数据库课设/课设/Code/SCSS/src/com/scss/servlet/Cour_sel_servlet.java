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
import com.scss.database.manage.DBS_insert;
import com.scss.database.manage.DBS_select;
import com.scss.database.tables.Course_selection;
import com.scss.database.tables.Section_info;
import com.scss.database.tables.Select_info;
import com.scss.database.tables.Stu_info;

/**
 * Servlet implementation class Cour_sel_servlet
 */
@WebServlet("/cour_sel_servlet")
public class Cour_sel_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cour_sel_servlet() {
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
    	
		String op = request.getParameter("op");
		if (op.equals("select")) select(request, response);
		else if (op.equals("insert")) insert(request, response);
		else if (op.equals("delete")) delete(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置编码
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
				
		select(request, response);
	}
	
	private void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson =new Gson();
		String student_id = request.getParameter("student_id");
		if (student_id!=null) student_id=(student_id.equals("")?null:student_id);
		String section_id = request.getParameter("section_id");
		if (section_id!=null) section_id=(section_id.equals("")?null:section_id);
		String course_name = request.getParameter("course_name");
		if (course_name!=null) course_name=(course_name.equals("")?null:course_name);
		//查询已选课程
		Select_info sel_info = new Select_info();
		sel_info.setStudent_id(student_id);
		ArrayList<Select_info> select_list =DBS_select.select(sel_info);
		String data_select = gson.toJson(select_list);
		//查询未选课程
		Section_info sec_info = new Section_info();
		sec_info.setStudent_id(student_id);
		sec_info.setSection_id(section_id);
		sec_info.setCourse_name(course_name);
		ArrayList<Section_info> section_list =DBS_select.select(sec_info);
		String data_section = gson.toJson(section_list);
		//将查询到的数据返回
		request.setAttribute("data_select", data_select);
		request.setAttribute("data_section", data_section);
		RequestDispatcher rd = request.getRequestDispatcher("course_select.jsp");
		rd.forward(request, response);
	}
	private void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		String student_id = request.getParameter("student_id");
		System.out.println("insert:"+data);
		//插入操作		begin
		Gson gson =new Gson();
		Section_info sec_info = gson.fromJson(data, Section_info.class);
		ArrayList<Section_info> section_list =DBS_select.select(sec_info);
		sec_info=section_list.get(0);
		if (sec_info.getRes_capacity()>0) {
			Course_selection co_sel = new Course_selection();
			co_sel.setStudent_id(student_id);
			co_sel.setSection_id(sec_info.getSection_id());
			DBS_insert.insert(co_sel);
			request.setAttribute("select_success", "true");
		} else {
			request.setAttribute("select_failed", "false");
		}
		//end
		select(request, response);
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String data = request.getParameter("data");
		String student_id = request.getParameter("student_id");
		System.out.println("delete:"+data);
		//删除操作		begin
		Gson gson =new Gson();
		Section_info sec_info = gson.fromJson(data, Section_info.class);
		Course_selection co_sel = new Course_selection();
		co_sel.setStudent_id(student_id);
		co_sel.setSection_id(sec_info.getSection_id());
		DBS_delete.delete(co_sel);
		//end
		select(request, response);
	}
	
}
