<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ITube</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="../resources/script/jquery-3.1.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/script/global.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#mypage-contents-upload").html("");
		
	});
	function clickAdjust(){
		$("#mypage-contents-adjust").load("adjust",function(data){
			
			$("#mypage-contents-adjust").html(data);
			$("#mypage-contents-upload").html("");
			
		})
	}
	function clickUpload(){
		$("#mypage-contents-upload").load("upload",function(data){
			
			$("#mypage-contents-adjust").html("");
			$("#mypage-contents-upload").html(data);
			
		})
	}
	function clickBookmark(){
		var uid = ${login.uid}
		$("#mypage-contents-bookmark").load("bookmark", {"uid" : uid }, function(data){
			$("#mypage-contents-upload").html("");
			$("#mypage-contents-adjust").html("");
			$("#mypage-contents-bookmark").html(data);
		})
	}
</script>
<style type="text/css">
	#mypage-info{
		margin-top: 70px;
	}
	#nav-menu li{
		text-align: right;
	}
	
</style>
</head>
<body data-spy="scroll" data-target=".navbar">
	<form action="../admin/" method="post" id="adminform"></form>
	<header>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-header">
				<a href="../" class="navbar-brand" id="logo" style="position: relative;bottom:11px"><img src="../resources/images/logoout.png"></a>
			</div>
			<ul class="nav navbar-nav navbar-left">
				<li>
					<select class="form-control" style="margin-top:8px">
						<option>제목</option>
						<option>내용</option>
						<option>제목+내용</option>						
					</select>
				</li>
				<li>
					<input type=text class="form-control" style="margin-top:8px" size="50" placeholder="검색" id="search">
				</li>
				<li>
					<a href="#"> <span class="glyphicon glyphicon-search"></span></a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right" style="margin-right:10px">
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
		</nav>
	</header>
	<div id="mypage-info" class="container-fluid" >
		<h1>마이 페이지</h1>
	</div>
	
	<div>
		<div class="row">
			<nav class="col-md-1">
				<ul class="nav nav-phills nav-stacked" data-spy="affix" data-offset-top="205" id="nav-menu">
					
					<li><a onclick="clickAdjust()">회원정보관리</a></li>
					<li><a onclick="clickUpload()">동영상 업로드</a></li>
					<li><a onclick="clickBookmark()">즐겨찾기 목록</a></li>
					<li><a href="#">최근 본 동영상</a></li>
					
				</ul>
			</nav>
			<div class="col-md-8" id="mypage-contents-adjust">
				<%@include file="../mypage/mypagemember.jsp" %>
			</div>
			<div class="col-md-8" id="mypage-contents-upload">
				<%@include file="../upload/upload.jsp" %>
			</div>
			<div class="col-md-8" id="mypage-contents-bookmark">
			</div>
		</div>
	</div>
	<footer></footer>
</body>
</html>