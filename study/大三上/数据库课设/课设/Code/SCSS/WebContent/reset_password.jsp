<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.scss.database.tables.*" %>
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
        
        <li class="layui-nav-item layui-nav-itemed">
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
  
  <div class="layui-body">
    <!-- 内容主体区域 -->
	
	<form class="layui-form" action="reset_password" style="padding: 15%;padding-left: 25%;padding-right: 25%" method="post">
	
		<input type="hidden" name="user_id" value="<%=user.getUser_id() %>">
		<div class="layui-form-item">
	    	<label class="layui-form-label">原密码：</label>
	    	<div class="layui-input-block">
<!-- 	    		<input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="原密码" class="layui-input"> -->
	    		<input type="password" name="old_password" lay-verify="od_password" placeholder="原密码" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">新密码：</label>
	    	<div class="layui-input-block">
<!-- 	      		<input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="新密码" class="layui-input"> -->
	    		<input type="password" name="new_password" lay-verify="password" placeholder="新密码" autocomplete="off" class="layui-input" id="new_pass">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">再次输入：</label>
	    	<div class="layui-input-block">
<!-- 	      		<input type="text" name="title" lay-verify="title" autocomplete="off" placeholder="再次输入新密码" class="layui-input"> -->
	      		<input type="password" name="password_again" lay-verify="password_again" placeholder="再次输入新密码" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
    		<div class="layui-input-block" style="padding-left: 27%">
      			<button class="layui-btn" lay-submit="" lay-filter="demo1">修改密码</button>
    		</div>
  		</div>
		
	</form>
		
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

layui.use(['form'], function(){
	var form = layui.form;
	//自定义验证规则
	form.verify({
		od_password: function(value){
		    if (value!=<%=user.getPassword() %>){
		    	return '原密码错误'
		    }
		}
	  	,password: [
	    	/^[\S]{5,12}$/
	    	,'密码必须6到12位，且不能出现空格'
	  	]
	  	,password_again: function(value){
			var p = document.getElementsById("new_pass").values;
			console.log(p);
			console.log(value)
		    if (p!=value){
		    	return '密码输入不一致';
		    }
		}
	});
});


</script>
</body>
</html>