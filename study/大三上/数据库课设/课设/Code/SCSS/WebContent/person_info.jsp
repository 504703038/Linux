<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.scss.database.tables.*,java.sql.Date" %>
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
    
    <jsp:useBean id="user" class="com.scss.database.tables.User" scope="session" />
    <jsp:useBean id="stu_info" class="com.scss.database.tables.Stu_info" scope="session" />
    <jsp:useBean id="ins_info" class="com.scss.database.tables.Ins_info" scope="session" />
    <% 
		String role = (String)request.getAttribute("role");
    	System.out.print(role);
    	String id = "ID",
    		name = "姓名",
    		depart_name = "学院",
    		major_name = "专业",
    		id_card = "身份证号",
    		phone = "手机号",
    		sex = "性别";
    	Date birthday=null;
    	int clas = 0;
    	if (role.equals("0")){
    		Stu_info tmp = (Stu_info)request.getAttribute("stu_info");
    		stu_info.setBirthday(tmp.getBirthday());
    		stu_info.setClas(tmp.getClas());
    		stu_info.setDepart_name(tmp.getDepart_name());
    		stu_info.setStudent_id(tmp.getStudent_id());
    		stu_info.setId_card(tmp.getId_card());
    		stu_info.setMajor_name(tmp.getMajor_name());
    		stu_info.setStudent_name(tmp.getStudent_name());
    		stu_info.setPhone(tmp.getPhone());
    		stu_info.setSex(tmp.getSex());
    		
    		id = stu_info.getStudent_id();
    		depart_name = stu_info.getDepart_name();
    		major_name = stu_info.getMajor_name();
	    	id_card = stu_info.getId_card();
	    	name = stu_info.getStudent_name();
	    	phone = stu_info.getPhone();
	    	sex = stu_info.getSex();
	    	birthday = stu_info.getBirthday();
	    	clas = stu_info.getClas();
    	}
    	if (role.equals("1")){
    		Ins_info tmp = (Ins_info)request.getAttribute("ins_info");
    		ins_info.setBirthday(tmp.getBirthday());
    		ins_info.setDepart_name(tmp.getDepart_name());
    		ins_info.setInstructor_id(tmp.getInstructor_id());
    		ins_info.setId_card(tmp.getId_card());
    		ins_info.setInstructor_name(tmp.getInstructor_name());
    		ins_info.setPhone(tmp.getPhone());
    		ins_info.setSex(tmp.getSex());
    		
    		id = ins_info.getInstructor_id();
    		depart_name = ins_info.getDepart_name();
	    	id_card = ins_info.getId_card();
	    	name = ins_info.getInstructor_name();
	    	phone = ins_info.getPhone();
	    	sex = ins_info.getSex();
	    	birthday = ins_info.getBirthday();
    	}
    %>
    
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;"><% out.print(name+"("+id+")"); %></a>
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
        <li class="layui-nav-item"><a href="cour_sel_servlet?op=select&student_id=<%=id %>">学生选课</a></li>
        
        <li class="layui-nav-item">
          <a href="javascript:;">成绩查询</a>
          <dl class="layui-nav-child">
            <dd><a href="stu_grade_servlet?op=select&stuent_id=<%=id %>">历年成绩查询</a></dd>
            <dd><a href="stu_grade_servlet?op=select&stuent_id=<%=id %>&limi=<60">不及格成绩查询</a></dd>
          </dl>
       </li>
        <%} %>
        
      </ul>
    </div>
  </div>
  
  <div class="layui-body">
    <!-- 内容主体区域 -->

	
	<form class="layui-form" action="personal_info_select" style="padding: 8%;padding-right: 25%;padding-left: 25%;" method="post">
	
		
		<input type="hidden" name="role" value=<%=role %>>
	
		<div class="layui-form-item">
	    	<label class="layui-form-label"><%if (role.equals("0")) out.print("学号："); else out.print("工号："); %></label>
	    	<div class="layui-input-block">
	    		<input type="text" name="user_id" lay-verify="title" autocomplete="off" placeholder="<%=id %>" class="layui-input" readonly="true" value="<%=id %>">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">身份证号：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="id_card" lay-verify="title" autocomplete="off" placeholder="<%=id_card %>" class="layui-input" readonly="true" value="<%=id_card %>">
	    	</div>
		</div>
		<%if (role.equals("0")) {%>
		<div class="layui-form-item">
	    	<label class="layui-form-label">入学年份：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="clas" lay-verify="title" autocomplete="off" placeholder="<%=clas %>" class="layui-input" readonly="true" value="<%=clas %>">
	    	</div>
		</div>
		<%} %>
		<div class="layui-form-item">
	    	<label class="layui-form-label">学院：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="depart_name" lay-verify="title" autocomplete="off" placeholder="<%=depart_name %>" class="layui-input" readonly="true" value="<%=depart_name %>">
	    	</div>
		</div>
		<%if (role.equals("0")) {%>
		<div class="layui-form-item">
	    	<label class="layui-form-label">专业：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="major_name" lay-verify="title" autocomplete="off" placeholder="<%=major_name %>" class="layui-input" readonly="true" value="<%=major_name %>">
	    	</div>
		</div>
		<%} %>
		<div class="layui-form-item">
	    	<label class="layui-form-label">性别：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="sex" lay-verify="title" autocomplete="off" placeholder="<%=sex %>" class="layui-input" readonly="true" value="<%=sex %>">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">生日：</label>
	    	<div class="layui-input-inline">
			  	<input type="text" name="birthday" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" value="<%=birthday %>">
			</div>
		</div>
		
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">手机：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="phone" lay-verify="phone" autocomplete="off" placeholder="<%=phone %>" class="layui-input" value="<%=phone %>">
	    	</div>
		</div>
		
		<div class="layui-form-item">
    		<div class="layui-input-block" style="padding-left: 30%">
      			<button class="layui-btn" lay-submit="" lay-filter="demo1">保存</button>
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

layui.use(['form', 'layedit', 'laydate'], function(){
	  var form = layui.form
	,layer = layui.layer
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate;
	  
	  //日期
	  laydate.render({
	    elem: '#date'
	  });
	//自定义验证规则
	form.verify({
	  age: [
		  /^[0-9]*$/
		  ,'年龄输入不合法'
	  ]
	
	  ,phone: [
	    /^\d{11}$/
	    ,'请输入正确的手机号'
	  ]
	});
});

function info_saved() {
	layer.open({
        type: 1
        ,offset: 'auto' //垂直
        ,id: 'layerDemo'+'auto' //防止重复弹出
        ,content: '<div style="padding: 20px 100px;">'+ '修改成功' +'</div>'
        ,btn: '确定'
        ,btnAlign: 'c' //按钮居中
        ,shade: 0.3 //遮罩
        ,yes: function(){
          layer.closeAll();
        }
      });
}
<% 
	if (request.getAttribute("changed")!=null){
		out.print("window.onload = info_saved;");
	}
%>
</script>
</body>
</html>