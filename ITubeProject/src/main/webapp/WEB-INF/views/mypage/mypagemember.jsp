<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/script/global.js"></script>

<title></title>
</head>

<body>
	<header>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-header">
				<a href="../" class="navbar-brand" id="logo"
					style="position: relative; bottom: 11px"><img
					src="../resources/images/logoout.png"></a>
			</div>
			<ul class="nav navbar-nav navbar-left">
				<li><select class="form-control" style="margin-top: 8px">
						<option>검색 필터</option>
						<option>제목</option>
						<option>내용</option>
						<option>제목+내용</option>
				</select></li>
				<li><input type=text class="form-control"
					style="margin-top: 8px" size="50" placeholder="검색" id="search">
				</li>
				<li><a href="#"> <span class="glyphicon glyphicon-search"></span></a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right" style="margin-right: 10px">
					<c:if test="${login != null }">
					<li>
						<a href="../logout">Logout</a>
					</li>
				</c:if>
			</ul>
		</nav>
	</header>
	<c:if test="${login != null }">
	
	<div id="content" class="panel-group container" style="padding-top: 0px; width: 760px">
		<div class="jumbotron">
			<div class="container text-center">
				<h1>회원정보수정</h1>
			</div>
		</div>
		<div id="content-header" class="panel panel-primary">
			<form class="panel-heading" method="post" action="memberModify" id="userForm">
				<div class="well well-lg">
					<label for="mypageId" style="color: black">ID</label><br>
					<input type="text" class="form-control" id="mypageId" value="${login.id }"  disabled="disabled" ><br>
					<label for="mypageNickName" style="color: black">NickName</label><br>
						<div>
							<input type="button" class="form-control btn-info" id="mypageNickname_check" value="중복확인"	style="width: 20%; float: right;">
							<input type="text" class="form-control" id="mypageNickName" name="member_nickname" style="width: 78%" value="${login.nickname }"><br>
						</div>
					<label for="mypagePassword" style="color: black">Password</label><br>
					<input type="password" class="form-control" id="mypagePassword" name="member_password"><br>
					<label for="mypagePasswordConfirm" style="color: black">Password Confirm</label><br>
					<input type="password" class="form-control" id="mypagePasswordConfirm" ><br>
					<label for="mypageEmail" style="color: black">Email</label><br>
						<div>
							<input type="button" class="form-control btn-info" id="mypageEmail_check" value="중복확인" style="width: 20%; float: right;">
							<input type="email" class="form-control" id="mypageEmail" name="member_email" style="width: 78%;" value="${login.email }"><br>
						</div>
					<input type="submit" class="form-control btn-warning" value="수정" style="width: 20%; margin-left: 40%" >
				</div>
				<input type="hidden" name="uid" value="${login.uid }">
				<input type="hidden" name="member_id" value="${login.id }">
				<input type="hidden" name="grade" value="${login.grade }">
			</form>
		</div>
	</div>
	</c:if>
</body>
</html> 