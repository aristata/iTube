<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<script>
	function join() {
		if($("#id").val() == "" || $("#nickname").val() ==""|| $("#password").val() ==""|| $("#email").val() ==""){
			alert("빈칸없이 입력하세요")
			return
		}else if($("#id_check_confirm").val() != "99"||$("#nickname_check_confirm").val() != "99"||$("#email_check_confirm").val() != "99"){
			alert("ID 또는 닉네임 또는 이메일 중복확인을 안했습니다.")
			return
		}else if(!$("#password_msg").hasClass("glyphicon glyphicon-ok")){
			alert("패스워드가 일치하지 않습니다.")
			return
		}
		$.ajax({
			url : "execute",
			method : "post",
			data : {
				id : $("#id").val(),
				pwd : $("#password").val(),
				nickname : $("#nickname").val(),
				email : $("#email").val()
			},
			success : function() {
				alert("회원가입이 완료됐습니다. 이메일 인증을 해주세요.")
				location.href="../login/login"
			},
			error : function() {
				alert("오류")
			}
		})
	}
	function multiplyCheck(type) {
		if (type == "id" && $("#id").val().search(" ") == -1 && $("#id").val() != "") {
			if(!($("#id").val().length < 11)){
				alert("10자 이하로 해야합니다.")
				return
			}
			$.post("idCheck", {
				id : $("#id").val()
			}, function(data) {
				if(data == "not"){
					$("#id_check_confirm").val("99")
					alert("사용가능한 아이디입니다.")
				}else{
					alert("사용불가능한 아이디입니다.")
				}
					
			})
		} else if (type == "nickname" && $("#nickname").val().search(" ") == -1
				&& $("#nickname").val() != "") {
			if(!($("#nickname").val().length < 11)){
				alert("10자 이하로 해야합니다.")
				return
			}
			$.post("nicknameCheck", {
				id : $("#nickname").val()
			}, function(data) {
				if(data == "not"){
					$("#nickname_check_confirm").val("99")
					alert("사용가능한 닉네임입니다.")
				}else{
					alert("사용불가능한 닉네임입니다.")
				}
					
			})
		} else if (type == "email" && $("#email").val().search(" ") == -1
				&& $("#email").val() != "") {
			if(!($("#email").val().length < 11)){
				alert("10자 이하로 해야합니다.")
				return
			}
			$.post("emailCheck", {
				id : $("#email").val()
			}, function(data) {
				if(data == "not"){
					$("#email_check_confirm").val("99")
					alert("사용가능한 이메일입니다.")
				}else{
					alert("사용불가능한 이메일입니다.")
				}
					
			})
		} else {
			alert("빈칸이 없어야 됍니다.")
		}
	}

	function passwordCheck() {
		if ($("#password").val() == $("#password_confirm").val()) {
			$("#password_msg").addClass("glyphicon glyphicon-ok", true)
			$("#password_msg").removeClass("fa fa-times", true)
		} else if ($("#password").val() != $("#password_confirm").val()){
			$("#password_msg").addClass("fa fa-times", true)
		}
	}
	
	function multiplyInit(type){
		if(type == "id" && $("#id_check_confirm").val() == "99"){
			$("#id_check_confirm").val("1")
		}else if(type=="nickname" && $("#nickname_check_confirm").val() == "99"){
			$("#nickname_check_confirm").val("1")
		}else if(type=="email" && $("#email_check_confirm").val() == "99"){
			$("#email_check_confirm").val("1")
		}
	}
</script>
</head>
<body>
	<div class="container" style="position: relative; top:100px;">
		<div class="jumbotron">
			<h1>Join</h1>
		</div>
		<div class="row well" style="width:1140px; margin-left: 1px;">
			<div class="col-sm-3">&nbsp;</div>
			<div class="col-sm-8">
				<form id="join_form">
					<div class="row">
						<div class="col-sm-6">
							<input type="text" class="form-control" id="id" placeholder="아이디"
								required="required" onkeyup="multiplyInit(id)">
						</div>
						<div class="col-sm-2">
							<input type="button" class="form-control btn-info" value="중복확인"
								id="id_check" onclick="multiplyCheck('id')">
								<input type="hidden" id="id_check_confirm">							
						</div>
					</div>
					<div class="row">
						<div class="col-sm-3">&nbsp;</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<input type="text" class="form-control" id="nickname"
								placeholder="닉네임" required="required" onkeyup="multiplyInit(nickname)">
						</div>
						<div class="col-sm-2">
							<input type="button" class="form-control btn-info" value="중복확인"
								id="nickname_check" onclick="multiplyCheck('nickname')">
							<input type="hidden" id="nickname_check_confirm">	
						</div>
					</div>
					<div class="row">
						<div class="col-sm-3">&nbsp;</div>
					</div>
					<div class="row">
						<div class="col-sm-4">
							<input type="password" class="form-control" id="password"
								placeholder="패스워드" required="required">
						</div>
						<div class="col-sm-4">
							<input type="password" class="form-control" id="password_confirm"
								placeholder="패스워드 확인" onkeyup="passwordCheck()">
						</div>
						<span id="password_msg"></span>
					</div>
					<div class="row">
						<div class="col-sm-3">&nbsp;</div>
					</div>
					<div class="row">
						<div class="col-sm-6">
							<input type="email" class="form-control" id="email"
								placeholder="이메일" required="required" onkeyup="multiplyInit(email)">
						</div>
						<div class="col-sm-2">
							<input type="button" class="form-control btn-info" value="중복확인"
								id="email_check" onclick="multiplyCheck('email')">
							<input type="hidden" id="email_check_confirm">	
						</div>
					</div>
					<div class="row">
						<div class="col-sm-3">&nbsp;</div>
					</div>
					<div class="row">
						<div class="col-sm-2">&nbsp;</div>
						<div class="col-sm-4">
							<input type="button" class="form-control btn-warning"
								value="회원가입" onclick="join()">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>
</html>