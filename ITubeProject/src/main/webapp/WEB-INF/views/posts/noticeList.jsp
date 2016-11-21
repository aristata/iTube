<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<div>
	<table class = "table table-notice">
		<tr>
			<td>nid</td>
			<td>ntitle</td>
			<td>nregdate</td>
		</tr>
		<c:forEach items="${list}" var="noticeVO">
			<tr>
				<td>${noticeVO.nid}</td>
				<td><a
					href='/posts/readNotice${pageMaker.makeQuery(pageMaker.cri.page)}&nid=${noticeVO.nid}'>
						${noticeVO.ntitle}</a></td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
						value="${noticeVO.nregdate}" /></td>
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
	<input type='hidden' name="page" value="${pageMaker.cri.perPageNum}">
	<input type='hidden' name="perPageNum" value="${pageMaker.cri.perPageNum}">
</form>

<script>
	$(".pagination li a").on("click", function(event) {

		event.preventDefault();

		var targetPage = $(this).attr("href");

		var jobForm = $("#jobForm");
		jobForm.find("[name='page']").val(targetPage);
		jobForm.attr("action", "/posts/noticeList").attr("method", "get");
		jobForm.submit();
	});
</script>