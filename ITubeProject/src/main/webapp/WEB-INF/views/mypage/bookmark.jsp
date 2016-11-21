<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	function goView(pid){
		$("#pid").val(pid)
		$("#viewform").submit()
	}
</script>
<form method="post" action="../view/" id="viewform"><input type="hidden" id="pid" name="pid"/></form>
<div>
	<table class="table posts_table" >
		<tr>
			<td>pid</td>
			<td>썸네일</td>
			<td>title</td>
			<td>작성자</td>
			<td>등록일</td>
			<td>조회수</td>
			<td>재생시간</td>
		</tr>
		<c:forEach items="${list}" var="postVO">
			<tr>
				<td>${postVO.pid}</td>
				<td>썸네일</td>
				<td><a onclick="goView('${postVO.pid}')">${postVO.title}</a></td>
				<td>${postVO.nickname}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
						value="${postVO.regdate}" /></td>
				<td><span class="badge bg-red">${postVO.viewcnt }</span></td>
				<td>${postVO.playtime }</td>
			</tr>
		</c:forEach>
	</table>
</div>