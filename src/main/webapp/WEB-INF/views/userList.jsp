<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>用户列表</title>

</head>

<body>
	<div>
		<form action="readUploadDocFile.html" method="post"  enctype="multipart/form-data">
			<input type="file" name="file" /> 
			<input type="submit" value="提交"/> sdsd
		</form>
	</div>
	<table>
		<thead>
			<tr>
				<td>用户名</td>
				<td>姓名</td>
				<td>邮箱</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="user">
				<tr>
					<td>${user.username}</td>
					<td>${user.realname}</td>
					<td>${user.email}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</body>

</html>