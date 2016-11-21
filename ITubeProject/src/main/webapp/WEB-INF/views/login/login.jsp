<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ITube</title>
<link rel="shortcut icon" href="/resources/images/logoout.png">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	function login() {
		//$.post("loginPost",{id:$("#id").val(),pwd:$("#password").val()},function(){location.href = "../"});
		 $.ajax({
			url : "./loginPost",
			type : "post",
			data : {
				id : $("#id").val(),
				pwd : $("#password").val()
			},
			success : function(data) {
				location.href = "../";
			},
			error : function(data) {
				alert("로그인 실패");
			}
		})
	}
</script>
</head>
<body>
	<c:if test="${path != null }">
		<input type="hidden" value="${path }" id="path-value"/>
	</c:if>
	<div class="container" style="position: relative; top:100px;">
		<div class="jumbotron">
			<h1>Login</h1>
		</div>
		<div class="row well" style="width:1140px; margin-left: 1px;">
			<div class="col-sm-3">&nbsp;</div>
			<div class="col-sm-8">
				<form>
					<div class="row">
						<div class="col-sm-8">
							<input type="text" class="form-control" id="id" placeholder="아이디"/>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-3">&nbsp;</div>
					</div>
					<div class="row">
						<div class="col-sm-8">
							<input type="password" class="form-control" id="password"
								placeholder="패스워드"/>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-3">&nbsp;</div>
					</div>
					<div class="row">
						<div class="col-sm-2">
							<input type="button" class="form-control btn-primary" value="로그인"
								onclick="login()"/>
						</div>
						<div class="col-sm-2">
							<a href="../join/"><input type="button"
								class="form-control btn-warning" value="회원가입"/></a>
						</div>
						<div class="col-sm-4">
							<input type="button" class="form-control btn-success"
								data-toggle="modal" data-target=".bs-find-modal-lg"
								value="아이디/비밀번호 찾기" id="id-find"/>
						</div>
					</div>
					<div class="modal fade bs-find-modal-lg" tabindex="-1"
						role="dialog" aria-labelledby="myLargeModalLabel"
						aria-hidden="true">

						<div class="modal-dialog modal-lg">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal"
											aria-label="Close">
											<span aria-hidden="true">&times;</span>
										</button>
										<h4 class="modal-title" id="exampleModalLabel">아이디 찾기</h4>
									</div>
									<div class="modal-body">
										<div class="form-group">
											<label for="idfind-id" class="control-label">이메일 주소로
												찾기 :</label> <input type="text" class="form-control" id="idfind-id"
												placeholder="example@email.com"> <br>
											<button type="button" class="btn btn-primary"
												style="position: relative; left: 500px;">보내기</button>
										</div>
									</div>
									<div class="modal-footer"></div>
									<div class="modal-header">
										<h4 class="modal-title" id="exampleModalLabel">비밀번호 찾기</h4>
									</div>
									<div class="modal-body">
										<div class="form-group">
											<label for="pwfind-id" class="control-label">아이디:</label> <input
												type="text" class="form-control" id="pwfind-id"
												placeholder="ID">
										</div>
										<div class="form-group">
											<label for="pwfind-mail" class="control-label">이메일
												주소:</label> <input type="text" class="form-control" id="pwfind-mail"
												placeholder="example@email.com"> <br>
											<button type="button" class="btn btn-primary"
												style="position: relative; left: 500px;">보내기</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>