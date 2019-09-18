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
    	String data_select = (String)request.getAttribute("data_select");
    	String data_section = (String)request.getAttribute("data_section");
    	if (data_select==null) data_select="[]";
    	if (data_section==null) data_section="[]";
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
        
        <li class="layui-nav-item">
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
	<h2 style="padding-left: 5%;padding-bottom: 2%;">学生选课</h2>
	<h3 style="padding-left: 5%;">已选课程</h3>
	<div style="padding-left: 5%;padding-right: 5%;">
		<table id="select" lay-filter="select"></table>
	</div>
	
	<form class="layui-form" action="cour_sel_servlet" method="post" style="padding-left: 15%;font-size: 16px;padding-top: 5%">
		<div class="layui-form-item">
			
			<input type="hidden" name="student_id" value="<%=user.getUser_id() %>">
			
			<div class="layui-inline">
		      	<label class="layui-form-label">课序号：</label>
		      	<div class="layui-input-inline" style="padding-top: 2%;">
		        	<input type="tel" name="section_id" autocomplete="off" class="layui-input" style="height: 25px;">
		      	</div>
	   		</div>
	   		
	   		<div class="layui-inline">
	      		<label class="layui-form-label">课程名称：</label>
		      	<div class="layui-input-inline" style="padding-top: 2%;">
		        	<input type="text" name="course_name" autocomplete="off" class="layui-input" style="height: 25px;">
		      	</div>
		    </div>
		    
		    <div class="layui-inline" style="padding-left: 5%;">
		      	<div class="layui-input-inline" >
		        	<button class="layui-btn" lay-submit="" lay-filter="demo1" style="text-align: center;height: 30px;">查询</button>
		      	</div>
		    </div>
	  	</div>
	</form>
	
	<h3 style="padding-left: 5%;">可选课程</h3>
	<div style="padding-left: 5%;padding-right: 5%;">
		<table id="course" lay-filter="course"></table>
	</div>
	
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
    © SCSS.com - 学生选课系统
  </div>
</div>


<script src="./layui/layui.js" charset="utf-8"></script>

<script type="text/html" id="barCourse">
  <a class="layui-btn layui-btn-xs" lay-event="select">选课</a>
</script>

<script type="text/html" id="barSelect">
  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="cancle">退选</a>
</script>

<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
});

layui.use('table', function(){
	var table = layui.table;
	table.render({
	    elem: '#select'
	    ,data:<%=data_select %>
	 		,cellMinWidth: 100
	    ,page: true //开启分页
	    ,cols: [[ //表头
	       {field: 'section_id', title: '课序号', sort: true}
	      ,{field: 'course_name', title: '课程名称', width:200}
	      ,{field: 'type', title: '课程类型', sort: true}
	      ,{field: 'credit', title: '学分', sort: true}
	      ,{field: 'year', title: '开课学年', sort: true}
	      ,{field: 'instructor_name', title: '老师', sort: true}
	      ,{field: 'begin_time', title: '开课时间', sort: true, width:120}
	      ,{field: 'end_time', title: '结课时间', sort: true, width:120}
	      ,{toolbar: '#barSelect', title:'操作', width: 70, fixed: 'right'}
	    ]]
	});
	  
	//监听工具条
	table.on('tool(select)', function(obj){
	  var data = obj.data;
	  if(obj.event === 'cancle'){
	      window.location.href="cour_sel_servlet?op=delete&student_id=<%=user_id %>&data="+encodeURIComponent(JSON.stringify(data));
	  } 
	});
	
	$('.demoTable .layui-btn').on('click', function(){
		  var type = $(this).data('type');
		  active[type] ? active[type].call(this) : '';
	});
});

layui.use('table', function(){
	var table = layui.table;
  	table.render({
	    elem: '#course'
	    ,data:<%=data_section %>
  		,cellMinWidth: 100
	    ,page: true //开启分页
	    ,cols: [[ //表头
	       {field: 'section_id', title: '课序号', sort: true}
	      ,{field: 'course_name', title: '课程名称', width:200}
	      ,{field: 'type', title: '课程类型', sort: true}
	      ,{field: 'credit', title: '学分', sort: true}
	      ,{field: 'year', title: '开课学年', sort: true}
	      ,{field: 'instructor_name', title: '老师', sort: true}
	      ,{field: 'res_capacity', title: '剩余课容量', sort: true}
	      ,{field: 'begin_time', title: '开课时间', sort: true, width:120}
	      ,{field: 'end_time', title: '结课时间', sort: true, width:120}
	      ,{toolbar: '#barCourse', title:'操作', width:70, fixed: 'right'}
	    ]]
	});
	  
	//监听工具条
	table.on('tool(course)', function(obj){
	  var data = obj.data;
	  if(obj.event === 'select'){
	      window.location.href="cour_sel_servlet?op=insert&student_id=<%=user_id %>&data="+encodeURIComponent(JSON.stringify(data));
	  } 
	});
	
	$('.demoTable .layui-btn').on('click', function(){
	  var type = $(this).data('type');
	  active[type] ? active[type].call(this) : '';
	});
});
	
</script>
</body>
</html>