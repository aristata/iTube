<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	function viewPost(pid){
		$("#pid").val(pid)
		$("#viewPost").submit()
	}
</script>
<form method="post" action="../view/" id="viewPost"><input type="hidden" id="pid" name="pid"/></form>
<div>
	<table class="table sposts_table" >
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
				<td><a onclick="javascript : viewPost(${postVO.pid})">${postVO.title} </a></td>
				<td>${postVO.nickname}</td>
				<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${postVO.regdate}" /></td>
				<td><span class="badge bg-red">${postVO.viewcnt }</span></td>
				<td>${postVO.playtime }</td>
			</tr>
		</c:forEach>
	</table>
</div>
<div class="box-footer">

	<div class="text-center">
		<ul class="pagination">

			<c:if test="${pageMaker.prev}">
				<li><a
					href="searchPage${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
			</c:if>

			<c:forEach begin="${pageMaker.startPage }"
				end="${pageMaker.endPage }" var="idx">
				<li <c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
					<a href="searchPage${pageMaker.makeSearch(idx)}">${idx}</a>
				</li>
			</c:forEach>

			<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
				<li><a
					href="searchPage${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
			</c:if>

		</ul>
	</div>
</div>