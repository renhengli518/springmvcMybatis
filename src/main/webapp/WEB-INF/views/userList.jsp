<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="org.apache.commons.lang.StringUtils"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<script src="${root }/static/js/extend.js" type="text/javascript"></script>
<script src="${root }/static/js/common.js" type="text/javascript"></script>
<script src="${root }/static/ueditor/ueditor.config.js" type="text/javascript"></script>
<script src="${root }/static/ueditor/ueditor.all.js" type="text/javascript" ></script>
<script src="${root }/static/ueditor/ueditor.parse.js" type="text/javascript" ></script>
<link media="all" type="text/css" rel="stylesheet" href="${root}/static/ueditor/themes/default/css/ueditor.css" />
<link media="all" type="text/css" rel="stylesheet" href="${root}/static/css/reset.css" />
<link media="all" type="text/css" rel="stylesheet" href="${root}/static/css/resetUE.css" />
<link media="all" type="text/css" rel="stylesheet" href="${root}/static/css/workplace.css" />
<link media="all" type="text/css" rel="stylesheet" href="${root}/static/css/workflat.css" />
<link media="all" type="text/css" rel="stylesheet" href="${root}/static/css/space.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>用户列表</title>

</head>

<body>
	<div class="space_main  clearfix">
		<div align="center">
			<form action="readUploadDocFile.html" method="post"
				enctype="multipart/form-data">
				<input type="file" name="file" /> <input type="submit" value="提交" />
				<input type="button" value="分页" onclick="pageList()" />
			</form>
		</div>
		<table align="center" width="100%" cellpadding="0" cellspacing="0">
			<thead>
				<tr>
					<td width="30%">用户名</td>
					<td width="30%">姓名</td>
					<td width="30%">邮箱</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${userList}" var="user">
					<tr>
						<td width="30%">${user.username}</td>
						<td width="30%">${user.realname}</td>
						<td width="30%">${user.email}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="noData">
		
		</div>
		
		<div class="page_yema" id="pageNavi">
		
		</div>
		<!-- <div id="editor">
		
		</div> -->
		
		<form method="post" id="ueditorForm" action="getUeditorForm.html">
			<script id="container" type="text/plain" name="ueditor" style="width:1024px;height:500px;">
		
			</script>
			<input type="submit" value="提交" />
		</form>
		<script type="text/javascript">
			//实例化编辑器
			var ue =  window.UE.getEditor("container", {});
		   /*  var ue = UE.getEditor('container',{onready:function(){//创建一个编辑器实例
			           this.setContent('hello');
			  }}); */
			  
			/*   function getContent(){
					alert(ue.getContentTxt());
				} */
		</script>
	</div>

</body>
<script type="text/javascript">
	$(document).ready(function() {

	});

 	function pageList() {
		config = {
			node : $id("pageNavi"),
			url : "pageListDemo.do",
			count : 20,
			data : {},
			callback : listInfosData,
			type : 'post'
		};

		mySplit = new SplitPage(config);
	}

	function listInfosData(data) {
		var len = data.length;
		//删除原来 的数据
		$("table tbody").html("");
		$("#noData").html("");
		var html = "";
		if (len > 0) {
			for (var i = 0; i < len; i++) {
				var infoObj = data[i];
				html += '<tr>' + '<td>' + infoObj.username + '</td>' + '<td>' + infoObj.realname + '</td>' + '<td>' + infoObj.email + '</td>' + '</tr>';
			}
		} else {
			$("#noData").text("没查到数据");
		}
		$("table tbody").append(html);
	}; 
	
	
</script>
</html>