<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<c:import url="../template/boot.jsp"></c:import>
</head>
<body>
<c:import url="../template/nav.jsp"></c:import>
	<div class="container">
		<h1>n o t i c e</h1>
		<h2>s e l e c t</h2>



		<form:form modelAttribute="noticeVO" id="frm"
			enctype="multipart/form-data">
			<div class="form-group">
				<label for="title">Title:${vo.title}</label>
			</div>

			<div class="form-group">
				<label for="writer">Writer: ${vo.writer}</label>
			</div>


			<div class="form-group">
				<label for="contents">Contents: ${vo.contents}</label>
			</div>

			<div class="form-group">
				<label for="regDate">Date: ${vo.regDate} </label>

			</div>
			<div class="form-group">
				<label for="hit">hit: ${vo.hit} </label>
			</div>

			<c:forEach items="${vo.noticeFilesVOs}" var="file">
				<h3>file: ${file.fname}</h3>
			</c:forEach>



		</form:form>
	</div>


</body>
</html>