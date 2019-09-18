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
import com.scss.database.tables.Course_section;
import com.scss.database.tables.Course_selection;
import com.scss.database.tables.Section_info;
import com.scss.database.tables.Stu_info;

/**
 * Servlet implementation class Section_mana_servlet
 */
@WebServlet("/section_mana_servlet")
public class Section_mana_servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Section_mana_servlet() {
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
    	//²Ù×÷
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
		String section_id = request.getParameter("section_id"); 
		if (section_id!=null) section_id=(section_id.equals("")?null:section_id);
		String course_name = request.getParameter("course_name"); 
		if (course_name!=null) course_name=(course_name.equals("")?null:course_name);
		Section_info sec = new Section_info();
		sec.setSection_id(section_id); sec.setCourse_name(course_name);
		ArrayList<Section_info> list =DBS_select.select(sec);
		String json = gson.toJson(list);
		request.setAttribute("data", json);
		RequestDispatcher rd = request.getRequestDispatcher("section_mana.jsp");
		rd.forward(request, response);
	}
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String data = request.getParameter("data");
		Course_section sec = gson.fromJson(data, Course_section.class);
		System.out.println("section_mana_servlet_update:"+data);
		//ÐÞ¸Ä²Ù×÷		begin
		
		
		//end
		select(request, response);
	}
	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		String data = request.getParameter("data");
		Course_section sec = gson.fromJson(data, Course_section.class);
		System.out.println("section_mana_servlet_delete:"+data);
		//É¾³ý²Ù×÷		begin
		DBS_delete.delete(sec);
		//end
		select(request, response);
	}

}
