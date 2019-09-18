<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>登录界面</title>
  <meta name="renderer" content="webkit">
  <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
  <link rel="stylesheet" href="./layui/css/layui.css"  media="all">
</head>
<body style="background: #dddddd; text-align: center;">

	<h1 style="padding-top: 100px;color: #666666;">学生选课系统</h1>
	<h2 style="padding-top: 15px;color: #666666;">登录界面</h2>
	
	<div style="padding-left: 38%;padding-right: 38%;padding-top: 3%;">
		<div style="background: #F0F0F0;">
	   		<form class="layui-form" action="Login" method="post" style="padding-top: 10%;padding-bottom: 10%;padding-right: 10%;">
			  <div>
			    <label class="layui-form-label" style="width: 15%;">工号：</label>
			    <div class="layui-input-block" style="margin-left: 25%;padding-bottom: 5px;padding-right: 5px;">
			      <input type="text" name="user_id" lay-verify="id" autocomplete="off" placeholder="请输入工号" class="layui-input">
			    </div>
			  </div>
	 
			  <div>
			    <label class="layui-form-label" style="width: 15%;">密码：</label>
			    <div class="layui-input-block" style="margin-left: 25%;padding-bottom: 5px;padding-right: 5px;">
			      <input type="password" name="password" lay-verify="password" placeholder="请输入密码" autocomplete="off" class="layui-input">
			    </div>
			  </div>
	 
			  <div style="padding-top: 3%;padding-left: 5%;">
			    <div class="layui-input-block" style="margin-left: 8%;">
			      <button class="layui-btn" lay-submit="">登录</button>
			    </div>
			  </div>
			</form>
  		</div>
	</div>
	


<script src="./layui/layui.js" charset="utf-8"></script>
<script>
layui.use(['form'], function(){
	var form = layui.form;
	//自定义验证规则
	form.verify({
	  id: function(value){
	    if(value.length < 5){
	      return '工号至少5个字符';
	    }
	  }
	  ,password: [
	    /^[\S]{5,12}$/
	    ,'密码必须6到12位，且不能出现空格'
	  ]
	});
});

function login_failed() {
	layer.open({
        type: 1
        ,offset: 'auto' //垂直
        ,id: 'layerDemo'+'auto' //防止重复弹出
        ,content: '<div style="padding: 20px 100px;">'+ '密码错误 ' +'</div>'
        ,btn: '确定'
        ,btnAlign: 'c' //按钮居中
        ,shade: 0.3 //遮罩
        ,yes: function(){
          layer.closeAll();
        }
      });
}
<% 
	if (request.getAttribute("login_failed")!=null){
		out.print("window.onload = login_failed;");
	}
%>
</script>

</body>
</html>