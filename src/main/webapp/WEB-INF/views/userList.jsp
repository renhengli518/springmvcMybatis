<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
<%
String contextPath = request.getContextPath();
if (contextPath.equals("/"))
	request.getSession().setAttribute("root", "");
else
	request.getSession().setAttribute("root", contextPath);
%>
<script src="${root}/static/js/jquery.js" type="text/javascript"></script>
<script src="${root}/static/js/splitpage.js" type="text/javascript"></script>
<script src="${root }/static/js/basiccheck.js" type="text/javascript"></script>
<script src="${root }/static/js/extend.js" type="text/javascript"></script>
<script src="${root }/static/js/common.js" type="text/javascript"></script>
<script src="${root }/static/js/ueditor/ueditor.config.js" type="text/javascript"></script>
<script src="${root }/static/js/ueditor/ueditor.all.min.js" type="text/javascript"></script>
<script src="${root }/static/js/jquery.cxscroll.js"></script>
<script src="${root }/static/js/WdatePicker.js" type="text/javascript"></script>
<link media="all" type="text/css" rel="stylesheet" href="${root}/static/css/reset.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${root}/static/css/resetUE.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${root}/static/css/workplace.css"/>
<link media="all" type="text/css" rel="stylesheet" href="${root}/static/css/workflat.css"/>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>用户列表</title>

</head>

<body>
	<div>
		<form action="readUploadDocFile.html" method="post"  enctype="multipart/form-data">
			<input type="file" name="file" /> 
			<input type="submit" value="提交"/>
			<input type="button" value="分页" onclick="pageList()"/>
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
	<div id="noData"></div>
	<div class="page_yema" id="pageNavi">
	</div> 
</body>
<script type="text/javascript">
	$(document).ready(function(){
		
	});
	
	function pageList(){
		config = {
				node : $id("pageNavi"),
			    url : "pageListDemo.do",
			    count : 20,
			    data : {},
			    callback : listInfosData,
			    type : 'post'};
		
		var mySplit = new SplitPage(config);
	}
	
	function listInfosData(data) {
		var len = data.length;
		//删除原来 的数据
		$("table tbody").html("");
		$("#noData").html("");
		var html = "";
		if(len>0){
			for(var i = 0; i< len; i++){
				var infoObj = data[i];
				html+='<tr>'
				+'<td>'+infoObj.username+'</td>'
				+'<td>'+infoObj.realname+'</td>'
				+'<td>'+infoObj.email+'</td>'
				+'</tr>';
			}
		} else {
			$("#noData").text("没查到数据");
		}
		$("table tbody").append(html);
	} ;
	
</script>
</html>