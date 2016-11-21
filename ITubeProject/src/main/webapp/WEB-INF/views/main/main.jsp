<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ITube</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/script/global.js"></script>
<script>
$(document).ready(function(){
	$("#side-bar").hide()
  $("#side-bar-btn").click(function(){
	  $("#side-bar").toggle(500)
	}) 
});

//search
function click_SearchBtn(){
	$.post("../posts/searchPage", {"searchType" : $("select option:selected").val(), "keyword" :$('#keywordInput').val() }, function(data){
		$("#hotPosts").html("");
		$("#categoryPosts").html("");
		$("#searchPosts").html(data);
	});
}

//음악 카테고리를 선택 했을때
function click_Category_Music(){
	$.get("/posts/listPage2",{"category":"음악"},function(data){
		$("#side-bar").hide();
		$("#hotPosts").hide();
		$("#categoryPosts").html(data);
	});
}

//게임 카테고리를 선택 했을때
function click_Category_Game(){
	$.get("/posts/listPage2",{"category":"게임"},function(data){
		$("#side-bar").hide();
		$("#hotPosts").hide();
		$("#categoryPosts").html(data);
	});
}

//이슈 카테고리를 선택 했을때
function click_Category_Issue(){
	$.get("/posts/listPage2",{"category":"이슈"},function(data){
		$("#side-bar").hide();
		$("#hotPosts").hide();
		$("#categoryPosts").html(data);
	});
}

//스포츠 카테고리를 선택 했을때
function click_Category_Sports(){
	$.get("/posts/listPage2",{"category":"스포츠"},function(data){
		$("#side-bar").hide();
		$("#hotPosts").hide();
		$("#categoryPosts").html(data);
	});
}

//기타 카테고리를 선택 했을때
function click_Category_Etc(){
	$.get("/posts/listPage2",{"category":"기타"},function(data){
		$("#side-bar").hide();
		$("#hotPosts").hide();
		$("#categoryPosts").html(data);
	});
}

</script>
</head>
<body data-spy="scroll" data-target=".navbar">
	<form action="../admin/" method="post" id="adminform"></form>
	<header>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-header">
				<button id="side-bar-btn" style="float: left; margin-top:14px; margin-right:-10px; margin-left:10px; background-color: #18191a; border-color: #18191a; border:0px; color:white;" ><i class="fa fa-bars" aria-hidden="true"></i></button>
				<a href=".." class="navbar-brand" id="logo" style="position: relative;bottom:11px"><img src="../resources/images/logoout.png"></a>
			</div>
			<ul class="nav navbar-nav navbar-left">
				<li><select name = "searchType" class="form-control" style="margin-top: 8px">
						<option value="t"
							<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
							제목</option>
						<option value="c"
							<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
							내용</option>
						<option value="w"
							<c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
							닉네임</option>
						<option value="tcw"
							<c:out value="${cri.searchType eq 'tcw'?'selected':''}"/>>
							모두</option>
				</select></li>
				<li>
					<input type=text class="form-control" style="margin-top:8px" size="50" placeholder="검색" id="keywordInput">
				</li>
				<li>
					<a id = "searchBtn" onclick="click_SearchBtn()"><span class="glyphicon glyphicon-search"></span></a>
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
	<!-- --------------------Header-------------------- 끝 -->
   
   
    <!-- --------------------Content-------------------- 시작 -->
	<div id="content" style="padding-top:80px; padding-left: 0px" class="container-fluid text-center">
		<div>
			<!-- 카테고리 부분 -->
			<div id="side-bar" class="col-sm-1 sidenav">
				<div>
					<div><a onclick="click_Category_Music()"><i class="fa fa-music" aria-hidden="true"></i>&nbsp;&nbsp;음악</a></div><br>
					<div><a onclick="click_Category_Game()"><i class="fa fa-gamepad" aria-hidden="true"></i>&nbsp;&nbsp;게임</a></div><br>
					<div><a onclick="click_Category_Issue()"><i class="fa fa-fire" aria-hidden="true"></i>&nbsp;&nbsp;이슈</a></div><br>
					<div><a onclick="click_Category_Sports()"><i class="fa fa-futbol-o" aria-hidden="true"></i>&nbsp;&nbsp;스포츠</a></div><br>
					<div><a onclick="click_Category_Etc()"><i class="fa fa-delicious" aria-hidden="true"></i>&nbsp;&nbsp;기타</a></div>
				</div>
			</div>
		</div>
		<br>

		<!-- 카테고리를 선택할때 바뀌는 부분 -->	
		<div class="container" id="hotPosts" >
			<%@ include file="hot.jsp" %>
		</div>
		<div class="container" id="categoryPosts">

		</div>
		<div class="container" id="searchPosts">
			  
		</div>
		<!-- --------------------------------------- -->
		<div class="col-sm-2 sidenav">
		</div>
	</div>

	<!-- --------------------Content-------------------- 끝 -->

	<footer></footer>
</body>
</html>