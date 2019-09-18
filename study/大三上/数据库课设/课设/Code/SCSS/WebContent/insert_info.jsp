<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<body>

	<%if (request.getParameter("student_training")!=null) {%>
	<form class="layui-form" action="insert_infor_servlet" style="padding: 8%;padding-right: 25%;padding-left: 25%;" method="post">
		
		<input type="hidden" name="info_type" value="student_training">
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">学号：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="student_id" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">类型：</label>
	    	<div class="layui-input-inline">
		        <select name="type" lay-verify="required" lay-search="">
		          <option value="">类型</option>
		          <option value="奖项">奖项</option>
		          <option value="处分">处分</option>
		        </select>
			</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">级别：</label>
	    	<div class="layui-input-inline">
		        <select name="level" lay-verify="required" lay-search="">
		          <option value="">级别</option>
		          <option value="院级">院级</option>
		          <option value="校级">校级</option>
		          <option value="省级">省级</option>
		          <option value="国家级">国家级</option>
		          <option value="世界级">世界级</option>
		        </select>
			</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">时间：</label>
			<div class="layui-input-inline">
			  	<input type="text" name="date" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">说明：</label>
			<div class="layui-input-block">
		      <textarea placeholder="请输入内容" class="layui-textarea" name="description"></textarea>
		    </div>
		</div>
		
		<div class="layui-form-item">
    		<div class="layui-input-block" style="padding-left: 30%">
      			<button class="layui-btn" lay-submit="" onclick="close_page()">确定</button>
    		</div>
  		</div>
		
	</form>
	<%} %>
	
	<%if (request.getParameter("student_info")!=null) {%>
	<form class="layui-form" action="insert_infor_servlet" style="padding: 8%;padding-right: 25%;padding-left: 25%;" method="post">
		
		<input type="hidden" name="info_type" value="student_training">
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">学生姓名：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="student_name" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">入学年份：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="clas" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">专业：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="major_id" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">性别：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="sex" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">生日：</label>
			<div class="layui-input-inline">
			  	<input type="text" name="birthd" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">身份证：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="id_card" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">手机：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="phone" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
    		<div class="layui-input-block" style="padding-left: 30%">
      			<button class="layui-btn" lay-submit="" onclick="close_page()">确定</button>
    		</div>
  		</div>
		
	</form>
	<%} %>
	
	<%if (request.getParameter("instructor_info")!=null) {%>
	<form class="layui-form" action="insert_infor_servlet" style="padding: 8%;padding-right: 25%;padding-left: 25%;" method="post">
		
		<input type="hidden" name="info_type" value="student_training">
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">教师姓名：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="student_name" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">学院：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="depart_id" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">性别：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="sex" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">生日：</label>
			<div class="layui-input-inline">
			  	<input type="text" name="birthd" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">身份证：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="id_card" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">手机：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="phone" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
    		<div class="layui-input-block" style="padding-left: 30%">
      			<button class="layui-btn" lay-submit="" onclick="close_page()">确定</button>
    		</div>
  		</div>
		
	</form>
	<%} %>
	
	<%if (request.getParameter("depart_info")!=null) {%>
	<form class="layui-form" action="insert_infor_servlet" style="padding: 8%;padding-right: 25%;padding-left: 25%;" method="post">
		
		<input type="hidden" name="info_type" value="student_training">
		
		
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">学院名称：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="depart_name" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">地址：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="address" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
    		<div class="layui-input-block" style="padding-left: 30%">
      			<button class="layui-btn" lay-submit="" onclick="close_page()">确定</button>
    		</div>
  		</div>
		
	</form>
	<%} %>
	
	<%if (request.getParameter("major_info")!=null) {%>
	<form class="layui-form" action="insert_infor_servlet" style="padding: 8%;padding-right: 25%;padding-left: 25%;" method="post">
		
		<input type="hidden" name="info_type" value="student_training">
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">学院编号：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="depart_id" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">专业名称：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="major_name" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">学年制：</label>
	    	<div class="layui-input-inline">
		        <select name="type" lay-verify="required" lay-search="">
		          <option value="">4年</option>
		          <option value="3年">3年</option>
		          <option value="4年">4年</option>
		          <option value="6年">6年</option>
		        </select>
			</div>
		</div>
		
		<div class="layui-form-item">
    		<div class="layui-input-block" style="padding-left: 30%">
      			<button class="layui-btn" lay-submit="" onclick="close_page()">确定</button>
    		</div>
  		</div>
		
	</form>
	<%} %>

	<%if (request.getParameter("course_info")!=null) {%>
	<form class="layui-form" action="insert_infor_servlet" style="padding: 8%;padding-right: 25%;padding-left: 25%;" method="post">
		
		<input type="hidden" name="info_type" value="student_training">
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">开课学院：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="depart_id" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">课程名称：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="course_name" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">课程类型：</label>
	    	<div class="layui-input-inline">
		        <select name="type" lay-verify="required" lay-search="">
		          <option value="">课程类型</option>
		          <option value="必修">必修</option>
		          <option value="选修">选修</option>
		          <option value="限选">限选</option>
		          <option value="通选">通选</option>
		        </select>
			</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">学分：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="credit" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		
		
		<div class="layui-form-item layui-form-text">
			<label class="layui-form-label">说明：</label>
			<div class="layui-input-block">
		      <textarea placeholder="请输入内容" class="layui-textarea" name="description"></textarea>
		    </div>
		</div>
		
		<div class="layui-form-item">
    		<div class="layui-input-block" style="padding-left: 30%">
      			<button class="layui-btn" lay-submit="" onclick="close_page()">确定</button>
    		</div>
  		</div>
		
	</form>
	<%} %>
	
	<%if (request.getParameter("section_info")!=null) {%>
	<form class="layui-form" action="insert_infor_servlet" style="padding: 8%;padding-right: 25%;padding-left: 25%;" method="post">
		
		<input type="hidden" name="info_type" value="student_training">
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">课程名称：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="course_name" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">开课学年：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="year" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">开课学期：</label>
	    	<div class="layui-input-inline">
		        <select name="semester" lay-verify="required" lay-search="">
		          <option value="">开课学期</option>
		          <option value="春">春</option>
		          <option value="秋">秋</option>
		        </select>
			</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">课容量：</label>
	    	<div class="layui-input-block">
	      		<input type="text" name="capacity" lay-verify="required" autocomplete="off" class="layui-input">
	    	</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">开课时间：</label>
			<div class="layui-input-inline">
			  	<input type="text" name="begin_time" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
			</div>
		</div>
		
		<div class="layui-form-item">
	    	<label class="layui-form-label">结课时间：</label>
			<div class="layui-input-inline">
			  	<input type="text" name="end_time" id="date" lay-verify="date" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input">
			</div>
		</div>
		
		
		<div class="layui-form-item">
    		<div class="layui-input-block" style="padding-left: 30%">
      			<button class="layui-btn" lay-submit="" onclick="close_page()">确定</button>
    		</div>
  		</div>
		
	</form>
	<%} %>
	
	
	
	
<script src="./layui/layui.js" charset="utf-8"></script>
<script>
//JavaScript代码区域
layui.use(['form', 'layedit', 'laydate'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate;
  
  //日期
  laydate.render({
    elem: '#date'
  });
  
});

function close_page(){
	console.log(123);
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

</script>
</body>
</html>