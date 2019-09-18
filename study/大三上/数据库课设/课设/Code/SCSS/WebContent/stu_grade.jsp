<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.scss.database.tables.*,java.io.*" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>学生选课系统</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="./layui/css/layui.css"  media="all">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
    <div class="layui-logo">学生选课系统 © SCSS </div>
    
    <jsp:useBean id="user" class="com.scss.database.tables.User" scope="session"></jsp:useBean>
    <% 
    	String user_id="ID",name="姓名";
    	if (user!=null){
    		if (user.getRole()==0){
    			user_id=user.getUser_id();
    			name=user.getStudent().getStudent_name();
    		}
    		if (user.getRole()==1){
    			user_id=user.getUser_id();
    			name=user.getInstructor().getInstructor_name();
    		}
    		if (user.getRole()==2){
    			user_id="admin";
    			name="教务";
    		}
    	}
    %>
    <%
    	String data = (String)request.getAttribute("data");
    	if (data==null) data="[]";
    %>
    
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;"><% out.print(name+"("+user_id+")"); %></a>
        <dl class="layui-nav-child">
          <%if (user.getRole()!=2) {%>
            <dd><a href="personal_info_select?role=<%=user.getRole() %>&user_id=<%=user.getUser_id() %>">详细信息</a></dd>
            <%} %>
            <dd><a href="index.jsp">退出账号</a></dd>
        </dl>
      </li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
        
        <li class="layui-nav-item">
          <a class="" href="javascript:;">个人中心</a>
          <dl class="layui-nav-child">
          	<%if (user.getRole()!=2) {%>
            <dd><a href="personal_info_select?role=<%=user.getRole() %>&user_id=<%=user.getUser_id() %>">详细信息</a></dd>
            <%} %>
            <dd><a href="reset_password.jsp">账号安全</a></dd>
          </dl>
        </li>
        
        <%if (user.getRole()!=0) {%>
         	<li class="layui-nav-item"><a href="student_training_info?op=select">学生奖惩信息管理</a></li>
        <%} %>
        <%if (user.getRole()==0) {%>
         	<li class="layui-nav-item"><a href="student_training_info?op=select&student_id=<%=user.getUser_id() %>">学生奖惩信息管理</a></li>
        <%} %>
        
        <%if (user.getRole()==2) {%>
        <li class="layui-nav-item">
          <a href="javascript:;">师生信息管理</a>
          <dl class="layui-nav-child">
            <dd><a href="stu_info_servlet?op=select">学生信息管理</a></dd>
            <dd><a href="ins_info_servlet?op=select">教师信息管理</a></dd>
          </dl>
        </li>
        
        <li class="layui-nav-item">
          <a href="javascript:;">院系信息管理</a>
          <dl class="layui-nav-child">
            <dd><a href="depart_info_servlet?op=select">学院信息管理</a></dd>
            <dd><a href="major_info_servlet?op=select">专业信息管理</a></dd>
          </dl>
        </li>
        <%} %>
        
        <%if (user.getRole()!=0) {%>
        <li class="layui-nav-item">
          <a href="javascript:;">课程信息管理</a>
          <dl class="layui-nav-child">
            <dd><a href="course_mana_servlet?op=select">课程开设管理</a></dd>
            <dd><a href="section_mana_servlet?op=select">开课信息管理</a></dd>
          </dl>
        </li>
        <%} %>
        
        
        <%if (user.getRole()==0) {%>
        <li class="layui-nav-item"><a href="cour_sel_servlet?op=select&student_id=<%=user_id %>">学生选课</a></li>
        
        <li class="layui-nav-item layui-nav-itemed">
          <a href="javascript:;">成绩查询</a>
          <dl class="layui-nav-child">
            <dd><a href="stu_grade_servlet?op=select&student_id=<%=user_id %>">历年成绩查询</a></dd>
            <dd><a href="stu_grade_servlet?op=select&student_id=<%=user_id %>&limi=<60">不及格成绩查询</a></dd>
          </dl>
       </li>
        <%} %>
        
      </ul>
    </div>
  </div>
  
  <div class="layui-body" style="padding-top: 3%">
	<!-- 内容主体区域 -->
	<h2 style="padding-left: 5%;padding-bottom: 2%;">成绩查询</h2>
	<h3 style="padding-left: 5%;">
		<%  String limi =request.getParameter("limi");
			if (limi==null) {
				out.print("历史成绩：");
			} else{
				out.print("不及格成绩：");
			}
		%>
	</h3>
	<div style="padding-left: 5%;padding-right: 5%;">
		<table id="demo" lay-filter="demo"></table>
	</div>
	
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © SCSS.com - 学生选课系统
  </div>
</div>


<script src="./layui/layui.js" charset="utf-8"></script>


<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
});


layui.use('table', function(){
	  var table = layui.table;
	  
	  table.render({
		    elem: '#demo'
		    ,data:<%=data %>
	  		,cellMinWidth: 50
		    ,page: true //开启分页
		    ,cols: [[ //表头
		       {field: 'year', title: '学年', sort: true}
		      ,{field: 'semseter', title: '学期', width:60}
		      ,{field: 'course_name', title: '课程名称', sort: true, width: 200}
		      ,{field: 'type', title: '课程类型', sort: true}
		      ,{field: 'credit', title: '学分', sort: true, width: 70}
		      ,{field: 'section_id', title: '课序号', sort: true}
		      ,{field: 'usual_grade', title: '平时成绩', sort: true}
		      ,{field: 'final_grade', title: '期末成绩', sort: true}
		      ,{field: 'grade', title: '总成绩', sort: true}
// 		      ,{field: 'gpa', title: '总成绩', sort: true}
// 		      ,{toolbar: '#barDemo', title:'操作', width:110, fixed: 'right'}
		    ]]
		});
});
	
</script>
</body>
</html>