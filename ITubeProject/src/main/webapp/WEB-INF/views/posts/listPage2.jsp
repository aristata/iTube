<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<%-- <%@include file="../include/header.jsp" %> --%>
<script>
	function goView(pid){
		$("#pid").val(pid)
		$("#viewform").submit()
	}
</script>
<form method="post" action="../view/" id="viewform"><input type="hidden" id="pid" name="pid"/></form>
		<!---------------- 카테고리가 게임일 때 ------------------ -->
		<c:if test="${pageMaker.cri.category == '게임'}">
			<div class="jumbotron" style="text-align: center; height: 180px; background-image: url('../resources/images/game.png')" id="board-head">
				<b style="font-family:'맑은 고딕'; font-weight: bold; font-size: 40pt; color: white">게 임</b>
			</div>
		</c:if>
		<!-- ----------------------------------------------- -->
		<!---------------- 카테고리가 음악일 때 ------------------ -->
		<c:if test="${pageMaker.cri.category == '음악'}">
			<div class="jumbotron" style="text-align: center; height: 180px; background-image: url('../resources/images/music.png')" id="board-head">
				<b style="font-family:'맑은 고딕'; font-weight: bold; font-size: 40pt; color: white">음 악</b>
			</div>
		</c:if>
		<!-- ----------------------------------------------- -->
		<!---------------- 카테고리가 이슈일 때 ------------------ -->
		<c:if test="${pageMaker.cri.category == '이슈'}">
			<div class="jumbotron" style="text-align: center; height: 180px; background-image: url('../resources/images/issue.png')" id="board-head" >
				<b style="font-family:'맑은 고딕'; font-weight: bold; font-size: 40pt; color: white">이 슈</b>
			</div>
		</c:if>
		<!-- ----------------------------------------------- -->
		<!---------------- 카테고리가 스포츠일 때 ------------------ -->
		<c:if test="${pageMaker.cri.category == '스포츠'}">
			<div class="jumbotron" style="text-align: center; height: 180px; background-image: url('../resources/images/sports.png')" id="board-head">
				<b style="font-family:'맑은 고딕'; font-weight: bold; font-size: 40pt; color: white">스포츠</b>
			</div>
		</c:if>
		<!-- ----------------------------------------------- -->
		<!---------------- 카테고리가 기타일 때 ------------------ -->
		<c:if test="${pageMaker.cri.category == '기타'}">
			<div class="jumbotron" style="text-align: center; height: 180px; background-image: url('../resources/images/etc.png')" id="board-head">
				<b style="font-family:'맑은 고딕'; font-weight: bold; font-size: 40pt; color: white">기 타</b>
			</div>
		</c:if>
		<!-- ----------------------------------------------- -->

<div>
	<table class="table posts_table">
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
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${postVO.regdate}" /></td>
				<td><span class="badge bg-red">${postVO.viewcnt }</span></td>
				<td>${postVO.playtime }</td>
			</tr>

		</c:forEach>
	</table>
</div>
<div class="Page-Line" align="center">
	<ul class="pagination">

		<c:if test="${pageMaker.prev}"> 
			<li><a href="${pageMaker.startPage - 1}">&laquo;</a></li>
		</c:if>

		<c:forEach begin="${pageMaker.startPage }" end="${pageMaker.endPage }"
			var="idx">
			<li <c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
				<a href="${idx}">${idx}</a>
			</li>
		</c:forEach>

		<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
			<li><a href="${pageMaker.endPage +1}">&raquo;</a></li>
		</c:if>

	</ul>
</div>
<form id="jobForm">
	<input type='hidden' name="page" value=${pageMaker.cri.perPageNum }>
	<input type='hidden' name="perPageNum" value=${pageMaker.cri.perPageNum }>
	<input type='hidden' name ="category" value=${pageMaker.cri.category }>
</form>

<script>
	$(".pagination li a").on("click", function(event) {

		event.preventDefault();

		var targetPage = $(this).attr("href");

		var jobForm = $("#jobForm");
		jobForm.find("[name='page']").val(targetPage);
		jobForm.attr("action", "/posts/listPage2").attr("method", "get");
		jobForm.submit();
	});
</script>