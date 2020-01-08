<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyPage</title>
<c:import url="../template/boot.jsp"></c:import>
</head>
<body>
<c:import url="../template/nav.jsp"></c:import>	


<div class="container">
	<h1>ID: ${member.id}</h1>
	<h1>Name: ${member.name}</h1>
	<h1>Email: ${member.email}</h1>
	<h1>Photo</h1>
	<h3>${member.memberFilesVO.fname}</h3>
	<h3>${member.memberFilesVO.oname}</h3>
	<img alt="" src="../member/${member.memberFilesVO.fname}">
	

	<div>
		<a href="./memberUpdate" class="btn btn-primary">Update</a>
		<a href="./memberDelete" class="btn btn-danger">Delete</a>
	</div>
	
</div>

</body>
</html>