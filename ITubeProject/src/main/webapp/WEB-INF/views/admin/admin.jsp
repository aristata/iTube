<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="../resources/script/jquery-3.1.1.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/script/global.js"></script>	
<style>
.navbar {
	margin-bottom: 0;
	border-radius: 0;
}

footer {
	background-color: #f2f2f2;
	padding: 25px;
}

#nav li {
	height: 50px;
}

.btn {
	margin-top: 8px;
}
</style>
<script>
 $(document).ready(function(){
	$("#noticeView").html("")
 })
	function userManagement(){
		$("#management").attr("action","userManagement")
		$("#management").submit()
	}
	function postManagement(){
		$("#management").attr("action","postManagement")
		$("#management").submit()
	}
	function userGradeModify(id){
		$.post("userGradeModify",{"userID":id,"userGrade":document.getElementById(id+'grade').value},function(){
			alert("수정이 완료되었습니다.")
			userManagement()
		})
	}
	function userDelete(id){
		$.post("userDelete",{"userID":id},function(){
			alert("삭제가 완료되었습니다.")
			userManagement()
		})
	}
	function gongji(){
		$("#noticeView").load("notice", function(data){
			$("#management").html("")
			$("#noticeView").html(data)
		})
	}

	function postCategoryModify(id){
		$.post("postCategoryModify", {"postID":id, "postCategory":document.getElementById(id+'category').value},function(){
			alert("수정이 완료되었습니다.")
			postManagement()
		})
	}
	
	function postDelete(id){
		$.post("postDelete", {"postID":id},function(){
			alert("삭제가 완료되었습니다.")
			postManagement()
		})
	}
	
	function viewPost(pid){
		$("#pid").val(pid)
		$("#viewPost").submit()
	}

</script>
</head>
<form method="post" action="../view/" id="viewPost"><input type="hidden" id="pid" name="pid"/></form>
<body>
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="../" class="navbar-brand" id="logo"
					style="position: relative; bottom: 11px;"><img
					src="../resources/images/logoout.png"></a>
			</div>
			<div class="collapse navbar-collapse" id="myNavbar">
				<ul class="nav navbar-nav">
					<li class="active"><a href="">관리 메뉴</a></li>
					<li>
						<button class="btn btn-primary dropdown-toggle" type="button"
							data-toggle="dropdown">게시판 관리</button>
						<ul class="dropdown-menu">
							<li id="gongji"><a onclick="gongji()">공지 사항</a></li>
							<li><a href="#">댓글 관리</a></li>
						</ul>
					</li>
					<li>
						<button class="btn btn-primary dropdown-toggle" type="button"
							data-toggle="dropdown" style="margin-top: 8px">동영상 관리</button>
						<ul class="dropdown-menu">
							<li><a onclick="postManagement()">동영상 목록 보기</a></li>
						</ul>
					</li>
					<li>
						<button class="btn btn-primary dropdown-toggle" type="button"
							data-toggle="dropdown">회원 관리</button>
						<ul class="dropdown-menu">
							<li><a onclick="userManagement()">회원 목록 보기</a></li>
						</ul>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
				<c:if test="${login != null }">
					<c:if test="${login.grade == 99 }">
						<li>
							<a href="javascript:document.getElementById('adminform').submit()">Admin</a>
						</li>
					</c:if>
					<li>
						<a href="../mypage/">MyPage</a>
					</li>
					<li>
						<a href="../logout">Logout</a>
					</li>
				</c:if>
				<c:if test="${login == null }">
				<li>
					<a href="../login/login">Login <span class="glyphicon glyphicon-log-in"></span></a>
				</li>
				</c:if>
			</ul>
			</div>
		</div>
	</nav>

	<div class="jumbotron">
		<div class="container text-center">
			<h1>ADMIN</h1>
		</div>
	</div>

	<div class="container">
		<form method="post" id="management">
		<c:if test="${userManagement != null }">
			<table class="table table-hover well">
				<thead>
					<tr>
						<td>UID</td>
						<td>ID</td>
						<td>닉네임</td>
						<td>이메일</td>
						<td>등급</td>
						<td>관리</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${userManagement }" var="userVO">
						<tr>
							<td>${userVO.uid }</td>
							<td>${userVO.id }</td>
							<td>${userVO.nickname }</td>
							<td>${userVO.email }</td>
							<td>${userVO.grade }</td>
							<td><input type="button" value="수정" class="btn btn-info"
								data-toggle="modal" data-target=".${userVO.id }">
								<div class="modal fade ${userVO.id }" role="dialog">
									<div class="modal-dialog">
										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">수정</h4>
											</div>
											<div class="modal-body">
												
												<label>ID</label> <input type="text" class="form-control"
													value="${userVO.id }" disabled="disabled" name="userID"> <label>Nickname</label>
												<input type="text" class="form-control"
													value="${userVO.nickname }" disabled="disabled" > <label>E-mail</label>
												<input type="text" class="form-control"
													value="${userVO.email }" disabled="disabled" > <label>Grade</label>
												<input type="text" class="form-control" 
													value="${userVO.grade }" id="${userVO.id }grade">
													<input type="hidden" id="${userVO.id }hidden" name="userGrade">
			
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal" onclick="userGradeModify('${userVO.id }')">Apply</button>
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
											</div>
										</div>
									</div>
								</div></td>
							<td><input type="button" value="삭제" class="btn btn-danger" onclick="userDelete('${userVO.id }')"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<c:if test="${postManagement != null }">
			<table class="table table-hover well">
				<thead>
					<tr>
						<td>PID</td>
						<td>제목</td>
						<td>닉네임</td>
						<td>생성날짜</td>
						<td>URL</td>
						<td>카테고리</td>
						<td>조회수</td>
						<td>관리</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${postManagement }" var="postVO">
						<tr>
							<td>${postVO.pid }</td>
							<td><a onclick="viewPost('${postVO.pid}')">${postVO.title }</a></td>
							<td>${postVO.nickname }</td>
							<td>${postVO.regdate }</td>
							<td>${postVO.url }</td>
							<td>${postVO.category }</td>
							<td>${postVO.viewcnt }</td>
							<td><input type="button" value="수정" class="btn btn-info"
								data-toggle="modal" data-target=".${postVO.pid }">
								<div class="modal fade ${postVO.pid }" role="dialog">
									<div class="modal-dialog">
										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">수정</h4>
											</div>
											<div class="modal-body">
												<label>TITLE</label>
												<input type="text" class="form-control" value="${postVO.title }" disabled="disabled">
												<label>NICKNAME</label>
												<input type="text" class="form-control" value="${postVO.nickname }" disabled="disabled">
												<label>DATE</label>
												<input type="text" class="form-control" value="${postVO.regdate }" disabled="disabled">
												<label>URL</label>
												<input type="text" class="form-control" value="${postVO.url }" disabled="disabled">
												<label>CATEGORY</label>
												
												<c:if test="${postVO.category == 'game'}">
													<div>
														<select class="form-control" id="${postVO.pid }category">
															<option>${postVO.category }</option>
															<option>음악</option>
															<option>이슈</option>
															<option>스포츠</option>
															<option>기타</option>
														</select>
													</div>
												</c:if>
												<c:if test="${postVO.category == '음악'}">
													<div>
														<select class="form-control" id="${postVO.pid }category">
															<option>${postVO.category }</option>
															<option>게임</option>
															<option>이슈</option>
															<option>스포츠</option>
															<option>기타</option>
														</select>
													</div>
												</c:if>
												<c:if test="${postVO.category == '이슈'}">
													<div>
														<select class="form-control" id="${postVO.pid }category">
															<option>${postVO.category }</option>
															<option>게임</option>
															<option>음악</option>
															<option>스포츠</option>
															<option>기타</option>
														</select>
													</div>
												</c:if>
												<c:if test="${postVO.category == '스포츠'}">
													<div>
														<select class="form-control" id="${postVO.pid }category">
															<option>${postVO.category }</option>
															<option>게임</option>
															<option>이슈</option>
															<option>음악</option>
															<option>기타</option>
														</select>
													</div>
												</c:if>
												<c:if test="${postVO.category == '기타'}">
													<div>
														<select class="form-control" id="${postVO.pid }category">
															<option>${postVO.category }</option>
															<option>게임</option>
															<option>이슈</option>
															<option>스포츠</option>
															<option>음악</option>
														</select>
													</div>
												</c:if>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-default"
													data-dismiss="modal" onclick="postCategoryModify('${postVO.pid }')">Apply</button>
												<button type="button" class="btn btn-default"
													data-dismiss="modal">Close</button>
											</div>
										</div>
									</div>
								</div></td>
							<td><input type="button" value="삭제" class="btn btn-danger" onclick="postDelete('${postVO.pid}')"></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		</form>
	</div>
	 <div id="noticeView">
			
	</div> 

</body><!-- 
<script>
	function gongji() {
		var contain = document.getElementById("container-body");
	}
</script> -->
</html>