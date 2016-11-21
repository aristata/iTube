<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ITube</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="./././resources/script/global.js"></script>
</head>
<body data-spy="scroll" data-target=".navbar">
	<header>
		<nav class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-header">
				<a href="" class="navbar-brand" id="logo" style="position: relative;bottom:11px"><img src="./././resources/images/logoout.png"></a>
			</div>
			<ul class="nav navbar-nav navbar-left">
				<li>
					<input type=text class="form-control" style="margin-top:8px" size="50" placeholder="검색" id="search">
				</li>
				<li>
					<a href="#"> <span class="glyphicon glyphicon-search"></span></a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right" style="margin-right:10px">
				<li>
					<a href="#">Login <span class="glyphicon glyphicon-log-in"></span></a>
				</li>
			</ul>
		</nav>
	</header>
	<div id=content>
	</div>
	<footer></footer>
</body>
</html>