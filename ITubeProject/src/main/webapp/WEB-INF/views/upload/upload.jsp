<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ITube</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="../resources/script/jquery-3.1.1.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../resources/script/global.js"></script>
<style type="text/css">
.filebox label {
	display: inline-block;
	padding: .5em .75em;
	color: #999;
	font-size: inherit;
	line-height: normal;
	vertical-align: middle;
	background-color: #fdfdfd;
	cursor: pointer;
	border: 1px solid #ebebeb;
	border-bottom-color: #e2e2e2;
	border-radius: .25em;
}

.filebox input[type="file"] {
	position: absolute;
	width: 1px;
	height: 1px;
	padding: 0;
	margin: -1px;
	overflow: hidden;
	clip: rect(0, 0, 0, 0);
	border: 0;
}
</style>
<script>
	$(document).ready(function(){
		$("#progress-parent").hide()
	})
function updateProgress(evt) {
    // evt is an ProgressEvent.
    if (evt.lengthComputable) {
      var percentLoaded = Math.round((evt.loaded / evt.total) * 100);
      // Increase the progress bar length.
      if (percentLoaded < 100) {
        progress.style.width = percentLoaded + '%';
        progress.textContent = percentLoaded + '%';
      }
    }
  }
	function fileLoad(accept, id) {
		$("#progress-parent").show()
		var progress = document.getElementById('progress');
		var reader = new FileReader();
		reader.onprogress = updateProgress;
		reader.onload = function(evt){
			progress.style.width = '100%';
	        progress.textContent = '100%';
	        
	        setTimeout(function(){
	        	progress.style.width = '0%';
		        progress.textContent = '0%';
	        	$("#progress-parent").hide(300)
	        },1000)
		};
		var fileName = document.getElementById(id).value
		var flag = false
		
		if (fileName.includes(accept)) {
				flag = true
			}
		if (flag) {
			reader.readAsBinaryString($("#"+id)[0].files[0])			
		} else {
			alert("지정된 파일 타입만 선택가능합니다.")
			$("#res").click()
			$("#progress-parent").hide()
		}
	}
	/* function uploadFile(){
		$.ajax({
			async: false,
			url: "../upload/execute",
			contentTypes: "multipart/form-data; charset=UTF-8",
			method: "POST",
			data:{title:$("#movie-title").val(),content:$("#movie-content").val(),nickname:$("#movie-nickname").val(),file:document.getElementById('file-select').files[0],category:$("#movie-category").val()},
			success: function(){
				alert("업로드 성공")
			},
			error: function(){
				alert("에러")
			}
		})
	} */
</script>
</head>
<body data-spy="scroll" data-target=".navbar">
	<header>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-header">
				<a href="../" class="navbar-brand" id="logo" style="position: relative;bottom:11px"><img src="../resources/images/logoout.png"></a>
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
   <!-- --------------------Header-------------------- 끝 -->
   
    <!-- --------------------Content-------------------- 시작 -->
	<div id="content" class="panel-group container" style="padding-top: 0px; width: 760px">
		<div class="jumbotron">
			<div class="container text-center">
				<h1>동영상 업로드</h1>
			</div>
		</div>
		<div id="content-header" class="panel panel-primary">
			<form class="panel-heading" method="post"
				enctype="multipart/form-data" action="../upload/execute">
				<div class="well well-lg">
					<!-- <label for="movie-title" style="color:black;">제목 : </label> -->
					<input type="text" class="form-control" id="movie-title"
						name="title" placeholder="제목 입력" required> <br>
					<!-- <label for="movie-contents" style="color:black;">내용 : </label> -->
					<textarea class="form-control" id="movie-contents" name="content"
						placeholder="내용 입력" required></textarea>
					<input type="hidden" id="movie-nickname" name="nickname" value="${login.nickname }">
				</div>
				<div class="well well-lg">
					<select class="form-control" name="category" id="movie-category"
						style="width: 25%; float: left;" required>
						<option>카테고리 선택</option>
						<option>음악</option>
						<option>스포츠</option>
						<option>게임</option>
						<option>영화</option>
						<option>뉴스</option>
						<option>TV프로그램</option>
					</select>
					<div class="filebox">
						<label for="file-select">파일 선택</label> <input type="file"
							id="file-select" name="file" accept=".mp4" onchange="fileLoad('.mp4','file-select')" required>
						<label for="thumbnail-select">썸네일 선택</label> <input type="file"
							id="thumbnail-select" name="thumbnail" accept=".png" onchange="fileLoad('.png','thumbnail-select')" required>	
						<div class="progress" id="progress-parent">
							<div class="progress-bar"
								 style="width: 0%" id="progress">
								0%</div>
						</div>
					</div>
					<div align="center">
						<button type="submit" class="btn btn-danger">업로드</button>
						<button type="reset" class="btn btn-info" id="res">다시 작성</button>
					</div>
				</div>
			</form>
		</div>
	</div>



	<footer></footer>
</body>
</html>