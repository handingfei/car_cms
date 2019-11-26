<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>用户注册</title>
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
</head>
<body>
<div class="container">
	<h1>用户注册</h1>
	<hr>
	<form action="" id="form1">
	
		<div class="form-group">
			<label>手机号码:</label>
			<input class="form-control" type="text" name="phone">
		</div>
		
		<div class="form-group">
			<label>登录密码:</label>
			<input class="form-control" type="password" name="password">
		</div>
		
		<div class="form-group">
			<label>司机姓名:</label>
			<input class="form-control" type="text" name="name">
		</div>
		
		<div class="form-group">
			<label>驾驶证号:</label>
			<input class="form-control" type="text" name="driverLicense">
		</div>
		
		<div class="form-group">
			<label>准驾车型:</label>
			<select id="driverType" name="driverType">
				<option>请选择</option>
			</select>
		</div>
		
		<div class="form-group">
			<label>发证日期:</label>
			<input class="form-control" type="date" name="created">
		</div>
		
		<div class="form-group">
		<button class="btn btn-info" onclick="add()" type="button">注册</button>
		</div>
		
	</form>
	
</div>
<script type="text/javascript">
	
	$(function () {
		$.get("/selectTypes",function(arr){
			for ( var i in arr) {
				$("#driverType").append("<option value='"+arr[i].code+"'>"+arr[i].code+"</option>");
			}
		})
	})
	
	function add() {
		var param = $("form1").serialize();
		
		$.ajax({
			url : "/reg",
			type : 'POST',
			data : param,
			success:function(flag){
				if (flag) {
					alert("注册成功")
					location.href="/login";
				} else {
					alert("注册失败")
				}
			}
			
			
		})
	}
	
	
	
	
	
</script>
</body>
</html>