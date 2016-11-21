<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	function viewSubmit(pid){
		$("#pid").val(pid)
		$("#viewform").submit()
	}
</script>
</head>
<body>
	<form method="post" action="../view/" id="viewform"><input type="hidden" id="pid" name="pid"/></form>
	<div class="row content">
		<div>
		<div class="jumbotron" style="text-align: center; height: 180px">
			<div>
				<b style="font-family:돋움; font-weight: bold; font-size: 30pt">${category}</b>
			</div>
		</div>
	</div>
	</div>
	<br>
	<form method="post" action="/categoryPage" id="postForm">
		<c:if test="${postlist != null }">
			<table class="table table-hover well">
				<thead>
					<tr>
						<td>제목</td>
						<td>글쓴이</td>
						<td>생성날짜</td>
						<td>카테고리</td>
						<td>조회수</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${postlist }" var="postVO">
						<tr>
							<td><a onclick="viewSubmit('${postVO.pid }')">${postVO.title }</a></td>
							<td>${postVO.nickname }</td>
							<td>${postVO.regdate }</td>
							<td>${postVO.category }</td>
							<td>${postVO.viewcnt }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</form>
	<c:if test="${postlist == null }">
		<script>
			$("#postForm").submit()
		</script>
	</c:if>
		
</body>
</html>