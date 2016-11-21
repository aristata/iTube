<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<div class="jumbotron" style="text-align: center; width:1140; height: 180px; background-image: url('../resources/images/best.png')">
				<b style="font-family:'맑은 고딕'; font-weight: bold; font-size: 40pt; color: white;">게시글 BEST TOP10</b>
		</div>
	<br>
	<form method="post" action="/hot" id="hotform">
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
			$("#hotform").submit()
		</script>
	</c:if>
		
</body>
</html>