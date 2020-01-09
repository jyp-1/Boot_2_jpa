<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

		<form action="./noticeList" id="frm">
			<input type="hidden" id="curPage" value="1" name="curPage"> 
			<select	id="kind" name="kind">
				<option id="kc" value="kc" selected="selected">CONTENTS</option>
				<option id="kt" value="kt">TITLE</option>
				<option id="kw" value="kw">WRITER</option>
			</select> <input type="text" id="search" name="search" value="${pager.search}">
			<button class="btn btn-basic">검색</button>
		</form>

		<div style="float: right;">
			<a href="./${board}Write" class="btn btn-danger">Write</a>
		</div>
		<br>
		<br>

		<table class="table table-hover">
			<tr>
				<td>Num</td>
				<td>Title</td>
				<td>Writer</td>
				<td>Date</td>
				<td>Hit</td>
				<!-- <td>file</td> -->
			</tr>

			<c:forEach items="${list}" var="vo">
				<tr>
					<td>${vo.num}</td>
					<td><a href="./${board}Select?num=${vo.num}">${vo.title}</a></td>
					<td>${vo.writer}</td>
					<td>${vo.regDate}</td>
					<td>${vo.hit}</td>
					<td><c:forEach items="${vo.noticeFilesVOs}" var="f">
							${f.fname}
						</c:forEach></td>
				</tr>

			</c:forEach>

		</table>


		<div>
			
	<ul class="pagination" style="margin: 0 auto; text-align: center;">
	
	<c:if test="${page.number gt 0}">
		<li><a href="./${board}List?page=${page.number-1}">이전</a></li>
	</c:if>
	
	
	<c:forEach begin="0" end="${page.totalPages}" varStatus="status">
		<li><a href="./${board}List?page=${status.index}">${status.index+1}</a></li>
	</c:forEach>
	
	<c:if test="${page.number lt page.totalPages-1}">
		<li><a href="./${board}List?page=${page.number+1}">다음</a></li>
	</c:if>
	
	
	</ul>
	
		</div>
	</div>


	<script type="text/javascript">
		var kind = '${pager.kind}'
		if (kind == '') {
			kind = "kt"
		}
		$("#" + kind).prop("selected", true);
		$(".list").click(function() {
			$("#curPage").val($(this).attr("id"));
			$("#frm").submit();
		});
	</script>



</body>
</html>