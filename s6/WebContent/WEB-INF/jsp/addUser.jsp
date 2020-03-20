<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
 <script type="text/javascript">
	function look() {
		var name=$("#name").val();
		var password=$("#password").val();
		var age=$("#age").val();
		var hobby=$("#hobby").val();
		
		if(name.length>5||name.length<2){
			 alert("姓名长度是2到5位") 
			 return false;
		}  
		if(password.length<6){
			alert("密码长度不少于6位")
			return false;
		}
		 
		if(age>120 || age<1){
			alert("年龄要在1到120岁哦")
			return false;
		}
 	   if(hobby.length<1){
	    alert("爱好不能为空哦")
	    return false;
		} 
		return true ;
	} 
</script> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="add" method="post" onsubmit="return look()">
		<!-- 编号:<input type="text" name="id" /><br />  -->
		姓名:<input type="text" name="name" id="name" /><br /> 
		密码:<input type="text"name="password" id="password"/><br /> 
		性别:<input type="radio"  name="sex"  value="男"  />男
      <input type="radio"  name="sex"  value="女"  />女<br/>
		年龄:<input type="text" name="age" id="age"/><br /> 
		爱好:<input type="text"name="hobby" id="hobby"/><br /> 
		<input type="submit" value="新增用户" /> 
		<a href="findAll">返回</a>
	</form>
</body>
</html>