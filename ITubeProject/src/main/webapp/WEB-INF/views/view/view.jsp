<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ITube</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<script type="text/javascript" src="../resources/script/global.js"></script>
<script>
function addReply(pid, nickname){
	$.ajax({
		url: "addReply",
		method: "POST",
		data:{"pid":pid,"content":$("#reply-content").val(),"nickname":nickname},
		success: function(data){
			$("#reply-list-table").html(data);
			var replycnt = parseInt($("#showReplyCnt").text()) + 1;
			$("#showReplyCnt").text(replycnt)
		},
		error: function(){
			alert("로그인 하세요")
		}
	})
}
function deleteReply(rid, pid){
	$.ajax({
		url: "deleteReply",
		method: "POST",
		data:{"rid":rid,"pid":pid},
		success: function(data){
			$("#reply-list-table").html(data)
			var replycnt = parseInt($("#showReplyCnt").text()) - 1;
			$("#showReplyCnt").text(replycnt)
		},
		error: function(){
			alert("에러")
		}
	})
} 

function clickLike(pid){
	$.ajax({
		url: "upDownCheck",
		method: "POST",
		data:{pid:pid,type:'1',msg:"like"},
		success: function(){
			$.post("clickLike",{"pid" : pid},function(){
				var likecnt = parseInt($("#likeCnt").text()) + 1;
				$("#likeCnt").text(likecnt);
			})
		},
		error: function(){
			alert("이미 투표했습니다.")
		}
	})
	
}

function clickDislike(pid){
	$.ajax({
		url: "upDownCheck",
		method: "POST",
		data:{pid:pid,type:'1',msg:"dislike"},
		success: function(){
			$.post("clickDislike",{"pid" : pid},function(){
				var dislikecnt = parseInt($("#dislikeCnt").text()) + 1;
				$("#dislikeCnt").text(dislikecnt);
			})
		},
		error: function(){
			alert("이미 투표했습니다.")
		}
	})
}
function likeReply(rid, pid){
	$.ajax({
		url: "upDownCheck",
		method: "POST",
		data:{rid:rid,pid:pid,type:'2',msg:"like"},
		success: function(){
			$.post("likeReply", {"rid" : rid, "pid" : pid}, function(){
				var likecnt = parseInt($("#likeCnt"+rid).text()) + 1;
				$("#likeCnt"+rid).text(likecnt);
			})
		},
		error: function(){
			alert("이미 투표했습니다.")
		}
	})
}
function disLikeReply(rid, pid){
	$.ajax({
		url: "upDownCheck",
		method: "POST",
		data:{rid:rid,pid:pid,type:'2',msg:"dislike"},
		success: function(){
			$.post("disLikeReply", {"rid" : rid, "pid" : pid}, function(){
				var dislikecnt = parseInt($("#dislikeCnt"+rid).text()) + 1;
				$("#dislikeCnt"+rid).text(dislikecnt);
			})
		},
		error: function(){
			alert("이미 투표했습니다.")
		}
	})
		
}
//즐겨찾기
function bookmark(pid, uid){
	var pid = '${pid}'
	var uid = '${login.uid}'
	if(uid != ""){
		$.post("bookmark", {"pid" : pid, "uid" : uid}, function(){
			 alert("추가되었습니다.")
		 })   
	}else{
		alert("비로그인")
	}
	   
}
</script>
<style type="text/css">
#content {
	padding-top: 70px;
}
</style>
</head>
<form method="post" action="addReply" id="replyform">
	<input type="hidden" id="pid" name="pid" value="${pid}"> <input type="hidden" id="content" name="content" value=""> <input type="hidden" id="nickname" name="nickname" value="">
</form>
<body data-spy="scroll" data-target=".navbar">
	<form action="../admin/" method="post" id="adminform"></form>
	<header>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-header">
				<a href=".." class="navbar-brand" id="logo" style="position: relative; bottom: 11px"><img src="../resources/images/logoout.png"></a>
			</div>
			<ul class="nav navbar-nav navbar-left">
				<li><select class="form-control" style="margin-top: 8px">
						<option>제목</option>
						<option>내용</option>
						<option>제목+내용</option>
				</select></li>
				<li><input type=text class="form-control" style="margin-top: 8px" size="50" placeholder="검색" id="search"></li>
				<li><a href="#"> <span class="glyphicon glyphicon-search"></span></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right" style="margin-right: 10px">
				<c:if test="${login != null }">
					<c:if test="${login.grade == 99 }">
						<li><a href="javascript:document.getElementById('adminform').submit()">Admin</a></li>
					</c:if>
					<li><a href="../mypage/">MyPage</a></li>
					<li><a href="../logout">Logout</a></li>
				</c:if>
				<c:if test="${login == null }">
					<li><a href="../login/login?path=">Login <span class="glyphicon glyphicon-log-in"></span></a></li>
				</c:if>
			</ul>
		</nav>
	</header>

	<div id="content" class="container">
		<div id="movie">
			<video width="960" height="516" controls="controls" preload="auto" poster="../resources/images/novideo.png" autoplay="autoplay">
				<source src="/view/${postVO.url }">
			</video>
		</div>
		<div class="panel-group" style="width: 960px;">
			<div id="title panel-default" class="panel panel-default">
				<div class="panel-heading">
					<div class="well well-lm">
						<label style="font-size: 20pt">${postVO.title }</label>
						<hr>
						<div class="panel-heading">
							<h4>작성자 : ${postVO.nickname }</h4>
							<label style="float: right; font-size: 20px">조회 수 : ${postVO.viewcnt }</label>
						</div>
					</div>
					<div class="well well-sm">
						<span onclick="bookmark()" class="glyphicon glyphicon-plus">추가&nbsp;</span> <span class="glyphicon glyphicon-share">공유</span> <span class="badge" style="float: right; width: 50px;" id="dislikeCnt">${postVO.down }</span> <span class="glyphicon glyphicon-thumbs-down" data-toggle="tooltip" title="이 글이 싫어요!" style="width: 20px; float: right;" onclick="clickDislike(${postVO.pid})"></span> <span style="float: right;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <span class="badge" style="float: right; width: 50px;" id="likeCnt">${postVO.up }</span> <span class="glyphicon glyphicon-thumbs-up" data-toggle="tooltip" title="이 글이 좋아요!" style="width: 20px; float: right;" onclick="clickLike(${postVO.pid})"></span>

					</div>
				</div>

				<div class="panel-body">
					<div class="well well-lm" style="background-color: white;">
						<label>게시일 : ${postVO.regdate }</label><br>
						<h4>${postVO.content }</h4>
					</div>
				</div>
			</div>

			<div class="panel panel-info">
				<div class="panel-heading">
					<label for="reply-comment" >댓글 [ <span id="showReplyCnt">${postVO.replycnt }</span> ]</label>
					<textarea class="form-control" id="reply-content" placeholder="댓글 추가.."></textarea>
					<button onclick="addReply(${postVO.pid },'${login.nickname }')">댓글 달기</button>
				</div>
				<div class="panel-body" id="reply-list">
					<select class="form-control" style="width: auto;">
						<option>인기 댓글순</option>
						<option>최근 댓글순</option>
					</select> <br>
					<table class="table table-reply" id="reply-list-table">
						<c:forEach items="${rList}" var="replyVO">
							<tr>
								<td><b style="font-family: '돋움체'; color: navy; position: relative; padding-left: 10px">${replyVO.nickname }</b></td>
								<td><p style="color: gray;">
										<fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${replyVO.regdate }" />
								</p></td>
								<td>
																	<!-- 게시글이 닉네임과 일치 할 때  --> 
								<c:if test="${login.nickname == replyVO.nickname || login.grade == 99}">
										<div class="dropdown" style="float: right">
											<button class="dropdown-toggle" data-toggle="dropdown" style="float: right; background-color: white; border: none;">
												<i class="fa fa-ellipsis-v " aria-hidden="true" style="float: right; padding-top: 3px"></i>
											</button>
											<ul class="dropdown-menu">
												<li><a onclick="updateReply(${replyVO.rid},${postVO.pid })">수정</a></li>
												<li><a onclick="deleteReply(${replyVO.rid},${postVO.pid })">삭제</a></li>
											</ul>
										</div>
									</c:if> <!-- ---------------------------------- --> <!-- 게시글이 닉네임과 일치하지 않을때  --> <c:if test="${login.nickname != replyVO.nickname && login.grade != 99}">
										<div class="dropdown" style="float: right">
											<button class="dropdown-toggle" data-toggle="dropdown" style="float: right; background-color: white; border: none;">
												<i class="fa fa-ellipsis-v " aria-hidden="true" style="float: right; padding-top: 3px"></i>
											</button>
											<ul class="dropdown-menu">
												<li><a href="">스팸신고</a></li>
											</ul>
										</div>
									</c:if> <!-- ---------------------------------- -->
								<span class="badge" style="float: right; width: 40px;" id="dislikeCnt${replyVO.rid }">${replyVO.down }</span> 
								<span class="glyphicon glyphicon-thumbs-down" data-toggle="tooltip" title="이 글이 싫어요!" style="width: 15px; float: right;" onclick="disLikeReply(${replyVO.rid},${postVO.pid })"></span> 
								<span style="float: right;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span> <span class="badge" style="float: right; width: 40px;" id="likeCnt${replyVO.rid }">${replyVO.up }</span> 
								<span class="glyphicon glyphicon-thumbs-up" data-toggle="tooltip" title="이 글이 좋아요!" style="width: 15px; float: right;" onclick="likeReply(${replyVO.rid},${postVO.pid })"></span>
								</td>
							</tr>
							<tr>
								<td style="border-top:none;"></td>
								<td colspan="2" style="border-top: none; font-family: '맑은고딕';">${replyVO.content }</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
	<footer></footer>
</body>
</html>