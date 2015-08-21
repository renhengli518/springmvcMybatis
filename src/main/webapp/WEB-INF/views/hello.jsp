<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>sprint hello</title>

</head>

<body>

	hello ${spring}!
	<c:if test="${not empty spring}">
		1
	</c:if>
	<c:if test="${empty spring}">
		2
	</c:if>
</body>

</html>