<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("tr:odd").css("background-color","lightgray");
});
</script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>高端用户</title>
<style type="text/css">
th{
	text-align: center;
}

td{
	text-align: center;
}

.td2 {
	text-align: right;
}

.table1 {
	border: 1px solid #ddd;
	width: 900px;
}

thead {
	background-color: lightblue;
}
</style>

</head>
<body>

	欢迎登陆：${currentUser}
	<br>
	<hr>

		<tr>
			<form action="selectByName" id="td1">
				<td colspan="4"><input type="text" name="name" /></td>
				<td colspan="3"><input type="submit" value="搜索姓名" /></td>
			</form>
		</tr>
		<tr>
			<h1 colspan="7"><a href="toAddUser">新增用户</a></h1>
		</tr> 
	<br>
	<c:if test="${empty requestScope.pagemsg}">
		没有任何用户信息！
	</c:if>
	<c:if test="${!empty requestScope.pagemsg}">
		<table border="1" cellpadding="10" cellspacing="0" class="table1">
			<thead>
				<tr>
					<th>id</th>
					<th>name</th>
					<th>sex</th>
					<th>age</th>
					<th>hobby</th>
					<th colspan="2">操作</th>
				</tr>
			</thead>

			<c:forEach items="${requestScope.pagemsg.lists}" var="user">
				<tr>
					<td>${user.id }</td>
					<td>${user.name}</td>
					<td>${user.sex}</td>
					<td>${user.age}</td>
					<td>${user.hobby }</td>
					<td><a href="selectById?id=${user.id }">修改</a></td>
					<td><a href="deleteById?id=${user.id }">删除</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
    
    <table  border="0" cellspacing="0" cellpadding="0"  width="900px"> 
<tr>
 
<td class="td2">
 
 
   <span>第${requestScope.pagemsg.currPage }/ ${requestScope.pagemsg.totalPage}页</span>  
   <span>总记录数：${requestScope.pagemsg.totalCount }  每页显示:${requestScope.pagemsg.pageSize}</span>  
   <span>
    <c:if test="${requestScope.pagemsg.currPage != requestScope.pagemsg.totalPage && requestScope.pagemsg.totalPage != 0}"></c:if> 
       <c:if test="${requestScope.pagemsg.currPage != 1}">
           <a href="${pageContext.request.contextPath }/findAll?currentPage=1">[首页]</a>  
           <a href="${pageContext.request.contextPath }/findAll?currentPage=${requestScope.pagemsg.currPage-1}">[上一页]</a>  
       </c:if>
       
       <c:if test="${requestScope.pagemsg.currPage != requestScope.pagemsg.totalPage}">
           <a href="${pageContext.request.contextPath }/findAll?currentPage=${requestScope.pagemsg.currPage+1}">[下一页]</a>  
           <a href="${pageContext.request.contextPath }/findAll?currentPage=${requestScope.pagemsg.totalPage}">[尾页]</a>  
       </c:if>
   </span>
</td>
</tr>
</table>
    
</body>
</html>